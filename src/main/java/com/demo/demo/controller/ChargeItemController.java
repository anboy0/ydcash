package com.demo.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
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
import com.demo.demo.log.bean.UserLogDict;
import com.demo.demo.response.ChargeItemStatus;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.dto.ChargeItemDto;
import com.demo.demo.sys.entity.ChargeItem;
import com.demo.demo.sys.service.IChargeItemService;
import com.demo.demo.tools.BaseUserUtils;

/**
 * <p>功能描述：费用项目管理控制器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午4:49:32
 */
@RestController
@RequestMapping("/chargeItem")
@Api(tags = "费用项目管理", description = "费用项目管理相关API")
public class ChargeItemController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IChargeItemService chargeItemService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "模糊查询费用项目", notes = "根据关键字查询费用项目列表")
    public Page<Map<String, Object>> list(@RequestBody ChargeItemDto dto) throws Exception {
        Page<Map<String, Object>> pager = new Page<Map<String, Object>>(dto.getPage(), dto.getPageSize());
        return chargeItemService.selectChargeItemPage(pager, dto);
    }

    @RequestMapping(value = "/treeList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取费用项目树", notes = "获取整颗费用项目树")
    public ChargeItem treeList() throws Exception {
        return chargeItemService.selectTreeList();
    }

    @RequestMapping(value = "/getChargeItem", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取费用项目", notes = "根据费用项目编码或者费用项目名称模糊查询")
    public List<Map<String, Object>> getChargeItem(String name,Integer excludeId) throws Exception {
        if (name == null || name.trim().length() == 0) {
            return null;
        }

        return chargeItemService.selectChargeItem(name,excludeId);
    }

    @RequestMapping(value = "/getChargeItemById", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取费用项目", notes = "根据ID获取费用项目")
    public Map<String, Object> getChargeItemById(Integer id) throws Exception {
        return chargeItemService.selectChargeItemById(id);
    }

    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "新增费用项目", key = "chargeItemName", dict = UserLogDict.class)
    @ResponseBody
    @ApiOperation(value = "新增费用项目", notes = "新增费用项目")
    public RestStatus add(@RequestBody ChargeItem dto) throws Exception {
    	validate(dto);
    	
        Date now = new Date();
        dto.setEnabled(true);//默认启用
        dto.setCreateBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setCreateTime(now);
        dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setModifyTime(now);
        dto.setEnabled(true);
        chargeItemService.insert(dto);
        return CommonStatus.ADD_OK;
    }

    @RequestMapping(value = "/edit", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "修改费用项目", key = "chargeItemName", dict = UserLogDict.class)
    @ResponseBody
    @ApiOperation(value = "修改费用项目", notes = "修改费用项目")
    public RestStatus edit(@RequestBody ChargeItem dto) throws Exception {
    	validate(dto);
    	
        Date now = new Date();
        dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setModifyTime(now);
        dto.setEnabled(true);
        chargeItemService.updateById(dto);
        return CommonStatus.UPDATE_OK;
    }
    
    @RequestMapping(value = "/delete", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "删除费用项目", key = "chargeItemName", dict = UserLogDict.class)
    @ResponseBody
    @ApiOperation(value = "删除费用项目", notes = "删除费用项目")
    public RestStatus delete(@RequestBody ChargeItem dto) throws Exception {
        List<ChargeItem>  chargeItem= chargeItemService.selectChildrenName(dto.getId());
        if(chargeItem!=null &&!chargeItem.isEmpty()){
            return CommonStatus.DELETE_FALSE;
        }
        Date now = new Date();
        dto.setModifyBy(BaseUserUtils.getCurrentBaseUserId());
        dto.setModifyTime(now);
        dto.setEnabled(false);
        chargeItemService.updateById(dto);
        return CommonStatus.DELETE_OK;
    }
    
    private void validate(ChargeItem dto) throws RestException{
       	Integer count = chargeItemService.selectRepeatCount(dto.getId(), dto.getChargeItemCode());
    	if(count!=null && count > 0){
    		throw new RestException(ChargeItemStatus.CHARGE_ITEM_CODE_REPEAT);
    	}
    	
    	String chargeItemName = dto.getChargeItemName();
    	if(chargeItemName == null || chargeItemName.trim().length()>15 || chargeItemName.trim().length()<1){
    		throw new RestException(ChargeItemStatus.CHARGE_ITEM_NAME_LENGTH);
    	}
    	
      	if(dto.getChargeItemCode() == null || !Pattern.matches("\\d{1,4}", dto.getChargeItemCode())){
    		throw new RestException(ChargeItemStatus.CHARGE_ITEM_CODE_LENGTH);
    	}
      	
    	if(dto.getRemark() != null && dto.getRemark().length()>100){
    		throw new RestException(CommonStatus.REMARK_LENGTH);

    	}
    }
}
