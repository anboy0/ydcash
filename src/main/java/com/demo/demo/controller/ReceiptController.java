package com.demo.demo.controller;

import com.demo.demo.sys.dto.CompanyDto;
import com.demo.demo.sys.entity.BaseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.aop.OperateLogAspect;
import com.demo.demo.exception.RestException;
import com.demo.demo.log.bean.ReceiptLogDict;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ReceiptStatus;
import com.demo.demo.response.ResponseData;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.dto.ReceiptDto;
import com.demo.demo.sys.entity.Receipt;
import com.demo.demo.sys.entity.ReceiptDetail;
import com.demo.demo.sys.service.IReceiptService;
import com.demo.demo.tools.BaseUserUtils;
import com.demo.demo.tools.DateUtil;

/**
 * <p>功能描述：收款管理控制器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午4:49:32
 */
@RestController
@RequestMapping("/receipt")
@Api(tags = "收款管理", description = "收款管理相关API")
public class ReceiptController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IReceiptService receiptService;

    private Double totalAmount = 0.0;

    /**
     * 收款信息查询
     *
     * @param offset
     * @param limit
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "模糊查询收据", notes = "根据关键字查询收款列表")
    public Page<Map<String, Object>> list(@RequestBody ReceiptDto dto) throws Exception {

        System.out.println(BaseUserUtils.getCurrentBaseUserId());

        if(dto.getStartTime() == null || dto.getEndTime() == null){
    		throw new RestException(ReceiptStatus.RECEIPT_TIME_LENGTH);
    	}

        CompanyDto q=receiptService.selectId(BaseUserUtils.getCurrentBaseUserId());
        BaseUser  user=receiptService.getBaseUserName(BaseUserUtils.getCurrentBaseUserId());
        //如果当前登陆用户为总部下面创建的员工
        if (user.getUsername().contains("admin") || (BaseUserUtils.getCurrentBaseUser().getCompanyCode().equals("568888"))){

        }else{
              //否则只能查询自己公司的数据
              dto.setCompanyid(q.getId());
        }
    	dto.setEndTime(DateUtil.getStrToDateTime(DateUtil.getDay(dto.getEndTime())+" 23:59:59"));
        Page<Map<String, Object>> pager = new Page<Map<String, Object>>(dto.getPage(), dto.getPageSize());

        return receiptService.selectReceiptPage(pager, dto);
    }

    @RequestMapping(value = "/getTotalAmount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取收款信息查询界面合计金额", notes = "条件查询总记录数汇总金额总和")
    public String getTotalAmount(@RequestBody ReceiptDto dto) throws Exception {

        System.out.println(BaseUserUtils.getCurrentBaseUserId());

        if(dto.getStartTime() == null || dto.getEndTime() == null){
            throw new RestException(ReceiptStatus.RECEIPT_TIME_LENGTH);
        }

        CompanyDto q=receiptService.selectId(BaseUserUtils.getCurrentBaseUserId());
        BaseUser  user=receiptService.getBaseUserName(BaseUserUtils.getCurrentBaseUserId());
        //如果当前登陆用户为总部下面创建的员工
        if (user.getUsername().contains("admin") || (BaseUserUtils.getCurrentBaseUser().getCompanyCode().equals("568888"))){

        }else{
            //否则只能查询自己公司的数据
            dto.setCompanyid(q.getId());
        }
        dto.setEndTime(DateUtil.getStrToDateTime(DateUtil.getDay(dto.getEndTime())+" 23:59:59"));

        return receiptService.queryTotalAmount(dto);
    }

    @RequestMapping(value = "/getReceiptById", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取收款信息", notes = "根据ID获取收款信息")
    public List<Map<String, Object>> list(int id) throws Exception {
        return (List<Map<String, Object>>) receiptService.selectReceiptById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "新增收款", key = "serialNo", dict = ReceiptLogDict.class)
    @ResponseBody
    //提交
    public ResponseData add(@RequestBody Receipt dto) throws Exception {
        totalAmount = 0.0;
    	validate(dto);
        if(totalAmount>1000000.00){
            throw new RestException(CommonStatus.AMOUNT_LIMIT);
        }
       /* CompanyDto q=receiptService.selectId(BaseUserUtils.getCurrentBaseUserId());
        if(q!=null) {
            dto.setCompanyid(q.getId());
        }*/
       dto.setStatus("1");//默认启用
       dto.setCreateBy(BaseUserUtils.getCurrentBaseUserId());
       dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
       dto.setCompanyid(dto.getReceiveCompanyId());
       receiptService.insertReceipt(dto);
       //上传成功后 把 原来为2 的数据给删除掉
       receiptService.deleteByReceipt(BaseUserUtils.getCurrentBaseUserId());
       return new ResponseData(dto,CommonStatus.ADD_OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "保存收款", key = "serialNo", dict = ReceiptLogDict.class)
    @ResponseBody
    //保存
    public ResponseData save(@RequestBody Receipt dto) throws Exception {
        totalAmount = 0.0;
        validate(dto);
        if(totalAmount>1000000.00){
            throw new RestException(CommonStatus.AMOUNT_LIMIT);
        }
        receiptService.deleteByReceipt(BaseUserUtils.getCurrentBaseUserId());
       /* CompanyDto q=receiptService.selectId(BaseUserUtils.getCurrentBaseUserId());
        if(q!=null) {
            dto.setCompanyid(q.getId());
        }*/
        dto.setStatus("2");//默认为保存信息
        dto.setCreateBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setCompanyid(dto.getReceiveCompanyId());
        receiptService.insertReceipt(dto);
        return new ResponseData(dto,CommonStatus.ADD_OK);
    }

    @RequestMapping(value = "/getCompanyInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取公司信息", notes = "根据当前登陆员工查询公司信息")
    //根据当前登陆员工查询公司信息
    public Map<String,Object> getCompanyInfo() throws Exception{
        System.out.println(receiptService.getCompanyInfo(BaseUserUtils.getCurrentBaseUserId()));
        return receiptService.getCompanyInfo(BaseUserUtils.getCurrentBaseUserId());
    }

    /**
     * 暂时保存的收款信息查询
     *
     * @param offset
     * @param limit
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/listCharge", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询数据", notes = "查询暂时保存的数据")
    public List<Map<String, Object>> listCharge(ReceiptDto dto) throws Exception {

        dto.setCreateBy(BaseUserUtils.getCurrentBaseUserId());
        return receiptService.selectReceiptPage1(dto);
    }



    @RequestMapping(value = "/writeOff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "冲销收款", key = "serialNo", dict = ReceiptLogDict.class)
    @ResponseBody
    public RestStatus writeOff(@RequestBody Receipt dto) throws Exception {
        dto.setStatus("0");//冲销
        dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setWritOffBy(BaseUserUtils.getCurrentBaseUserId());
        receiptService.updateReceipt(dto);
        return ReceiptStatus.WRITE_OFF_OK;
    }
    
    private void validate(Receipt dto) throws RestException{
    	if(dto.getPayCompanyType() !=null && "3".equals(dto.getPayCompanyType())){
        	String payName = dto.getPayName();
        	if(payName == null || payName.trim().length()>15 || payName.trim().length()<1){
        		throw new RestException(CommonStatus.NAME_LENGTH15);
        	}
    	}else{
        	if(dto.getPayCompanyId() == null){
        		throw new RestException(ReceiptStatus.RECEIPT_PAY_RECEIVE_COMPANY_NULL);
        	}
    	}
    	
    	if(dto.getReceiveCompanyId() == null){
    		throw new RestException(ReceiptStatus.RECEIPT_RECEIVE_COMPANY_NULL);
    	}
    	
    	List<ReceiptDetail> list = dto.getReceiptDetail();
    	for(ReceiptDetail detail : list){

    	    totalAmount += detail.getMoney();

          	if(detail.getEwbNo() != null && !"".equals(detail.getEwbNo().trim()) && !Pattern.matches("[0-9a-zA-Z]{1,30}", detail.getEwbNo())){
        		throw new RestException(ReceiptStatus.RECEIPT_EWB_NO_LENGTH);
        	}
          	
          	if(detail.getRemark() != null && detail.getRemark().length()>100){
        		throw new RestException(ReceiptStatus.RECEIPT_REMARK_LENGTH);
        	}
          	
        	if(detail.getChargeItemId() == null){
        		throw new RestException(ReceiptStatus.RECEIPT_CHARGE_ITEM_NULL);
        	}
    	}
        System.out.println(totalAmount);
    }
}
