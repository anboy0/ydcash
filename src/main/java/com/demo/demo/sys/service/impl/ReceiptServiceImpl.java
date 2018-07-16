package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.dto.CompanyDto;
import com.demo.demo.sys.dto.ReceiptDto;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.entity.Receipt;
import com.demo.demo.sys.entity.ReceiptDetail;
import com.demo.demo.sys.mapper.ReceiptMapper;
import com.demo.demo.sys.service.IReceiptService;
import com.demo.demo.tools.Arith;
import com.demo.demo.tools.SequenceConstant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述：收款service实现类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:22:30
 */
@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements IReceiptService {
    @Override
    public String selectSequence(String seqName) {
        return baseMapper.selectSequence(seqName);
    }

    @Override
    public Page<Map<String, Object>> selectReceiptPage(Page<Map<String, Object>> page, ReceiptDto dto) {
        return page.setRecords(baseMapper.selectReceiptPage(page, dto));
    }
    @Override
    public String queryTotalAmount(ReceiptDto dto) {
        return baseMapper.queryTotalAmount(dto);
    }
    @Override
    public  List<Map<String, Object>> selectReceiptPage1(ReceiptDto dto) {
        return baseMapper.selectReceiptPage1(dto);
    }
    @Override
    public List<Map<String, Object>> selectReceiptById(int id) {
        return baseMapper.selectReceiptById(id);
    }

    @Override
    public List<Map<String, Object>> selectReceiptDetailByIds(Map search) {
        return baseMapper.selectReceiptDetailByIds(search);
    }

    @Override
    public int updatePrint(Map search) {
        return baseMapper.updatePrint(search);
    }

    @Override
    public boolean insertReceipt(Receipt receipt) {
        // 1.获取流水号
        String serialNo = baseMapper.selectSequence(SequenceConstant.SEQ_SERIAL_NO);
        receipt.setSerialNo(serialNo);

        double totalMoney = 0;
        List<ReceiptDetail> receiptDetailList = receipt.getReceiptDetail();
        for (ReceiptDetail detail : receiptDetailList) {
            totalMoney = Arith.add(totalMoney, detail.getMoney());
        }
        receipt.setTotalMoney(totalMoney);
        
        // 2.插入收款数据
        baseMapper.insertReceipt(receipt);
        int receiptId = receipt.getId();

        // 3.插入收款明细数据
        for (ReceiptDetail detail : receiptDetailList) {
            detail.setReceiptId(receiptId);
        }
        
        baseMapper.insertReceiptDetail(receiptDetailList);
        return true;
    }

	@Override
	public int updateReceipt(Receipt receipt) {
		return baseMapper.updateReceipt(receipt);
	}

    @Override
    public CompanyDto selectId(int id) {
        return baseMapper.selectId(id);
    }

    @Override
    public BaseUser getBaseUserName(int id) {
        return baseMapper.getBaseUserName(id);
    }

    @Override
    public void deleteByReceipt(int id) {
         baseMapper.deleteByReceipt(id);
    }

    @Override
    public Map<String,Object> getCompanyInfo(Integer userId) {
        return baseMapper.getCompanyInfo(userId);
    }

}
