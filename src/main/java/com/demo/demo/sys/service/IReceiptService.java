package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.dto.CompanyDto;
import com.demo.demo.sys.dto.ReceiptDto;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.entity.Receipt;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述：收款service接口</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:22:30
 */
public interface IReceiptService extends IService<Receipt> {
    String selectSequence(String seqName);

    Page<Map<String, Object>> selectReceiptPage(Page<Map<String, Object>> page, ReceiptDto dto);

    String queryTotalAmount(ReceiptDto dto);

    List<Map<String, Object>> selectReceiptById(int id);

    List<Map<String,Object>> selectReceiptDetailByIds(Map search);
    int updatePrint(Map search);

    //add by xinglei
    List<Map<String, Object>> selectReceiptPage1(ReceiptDto dto);
    //add by xinglei
    void deleteByReceipt(int id);

    boolean  insertReceipt(Receipt receipt);

    CompanyDto selectId(int id);

    int updateReceipt(Receipt receipt);

    BaseUser getBaseUserName(int id);

    Map<String,Object> getCompanyInfo(Integer userId);

}
