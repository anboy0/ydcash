package com.demo.demo.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.demo.demo.sys.dto.CompanyDto;
import com.demo.demo.sys.dto.ReceiptDto;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.entity.Receipt;
import com.demo.demo.sys.entity.ReceiptDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述：收款mapper接口</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:20:26
 */
public interface ReceiptMapper extends BaseMapper<Receipt> {
    String selectSequence(String seqName);

    List<Map<String, Object>> selectReceiptPage(Pagination page, ReceiptDto dto);
    String queryTotalAmount(ReceiptDto dto);
    //add by xinglei 查询 暂时保存的 收款信息
    List<Map<String, Object>>selectReceiptPage1(ReceiptDto dto);
    //删除以上传的保存信息
    void deleteByReceipt(int id);
    List<Map<String, Object>> selectReceiptById(int id);
    List<Map<String,Object>> selectReceiptDetailByIds(Map search);
    int updatePrint(Map search);

    int insertReceipt(Receipt receipt);

    void insertReceiptDetail(List<ReceiptDetail> list);
    
    int updateReceipt(Receipt receipt);

    CompanyDto selectId(int id);

    BaseUser getBaseUserName(int id);

    Map<String,Object> getCompanyInfo(Integer userId);

}
