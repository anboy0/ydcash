package com.demo.demo.sys.service.impl;

import com.demo.demo.bus.Import.bean.ReceiptImportBean;
import com.demo.demo.sys.entity.Receipt;
import com.demo.demo.sys.entity.ReceiptDetail;
import com.demo.demo.sys.service.IBatchImportService;
import com.demo.demo.sys.service.IReceiptService;
import com.demo.demo.tools.BaseUserUtils;
import com.demo.demo.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BatchImportServiceImpl implements IBatchImportService{
    @Autowired
    IReceiptService receiptService;

    Map<String,Object> _batchImportReceiptTemp ;
    /**
     * 在原数据做截取付款公司列表
     * @param datas
     * @return
     */

    public List<String> getCmpList(List<ReceiptImportBean> datas) {
        List<String> cmp = new ArrayList<String>();
        for(ReceiptImportBean i:datas){
            boolean hasCmp = false;
            for(int z=0;z<cmp.size();z++){
                if(i.getCmpName().equals(cmp.get(z))){
                    hasCmp = true;
                    break;
                }
            }
            if(!hasCmp){
                cmp.add(i.getCmpName());
            }
        }
        return cmp;
    }
    /**
     * 在原数据做截取收款公司列表
     * @param datas
     * @return
     */
    public List<String> getRCmpList(List<ReceiptImportBean> datas) {
        List<String> cmp = new ArrayList<String>();
        for(ReceiptImportBean i:datas){
            boolean hasCmp = false;
            for(int z=0;z<cmp.size();z++){
                if(i.getReciveCmp().equals(cmp.get(z))){
                    hasCmp = true;
                    break;
                }
            }
            if(!hasCmp){
                cmp.add(i.getReciveCmp());
            }
        }
        return cmp;
    }


    /**
     * 根据付款公司名称获取对应于该公司的数据
     * @param rCmp  收款公司
     * @param pCmp  付款公司
     * @param datas
     * @return
     */
    private List<ReceiptImportBean> selectByCmps(String rCmp,String pCmp,List<ReceiptImportBean> datas){
        List<ReceiptImportBean> data = new ArrayList<ReceiptImportBean>();
        for(ReceiptImportBean i:datas){
            if(i.getCmpName().equals(pCmp)&&i.getReciveCmp().equals(rCmp)){
                data.add(i);
            }
        }
        return data;
    }
    private void slipData(List<String>pCmps,List<ReceiptImportBean> org){
        _batchImportReceiptTemp.clear();
        for (String pCmp:pCmps){
            List<ReceiptImportBean> data = new ArrayList<ReceiptImportBean>();
            for(ReceiptImportBean i:org){
                if(i.getCmpName().equals(pCmp)){
                    data.add(i);
                }
            }
            _batchImportReceiptTemp.put(pCmp,data);
        }
    }
    @Override
    @Transactional
    public List<Integer> doImPort(List<ReceiptImportBean> org,int size){
        List<Integer> iDs= new ArrayList<>();
        _batchImportReceiptTemp = getPCmp(org);
        for(Map.Entry<String,Object > entry:_batchImportReceiptTemp.entrySet()){
            // 获取付款公司列表
            List<ReceiptImportBean> deals = (List<ReceiptImportBean>) entry.getValue();
            Map<String,Object> _rCmpMaps = getRCmp(deals);
            for(Map.Entry<String,Object> entry1: _rCmpMaps.entrySet()){
                // 获取付款公司列表
                List<ReceiptImportBean> _d = (List<ReceiptImportBean>) entry1.getValue();
                Map<String,Object> _dateMaps = getDate(_d);
                for(Map.Entry<String,Object> entry2: _dateMaps.entrySet()){
                    //获取日期列表
                    List<ReceiptImportBean> hands = (List<ReceiptImportBean>) entry2.getValue();
                    for(int d =0;d<hands.size();d+=size){
                        ReceiptImportBean tmp = hands.get(d);
                        List<ReceiptDetail> receiptDetail = new ArrayList<>();
                        double sum =0.0;
                        int len = (hands.size()-d)/size;
                        if(len>0){
                            len= size;
                        }else{
                            len = (hands.size()-d)%size;
                        }
                        for (int y = 0; y <len ; y++) {
                            ReceiptImportBean bean = hands.get(d+y);
                            ReceiptDetail dt = new ReceiptDetail();
                            sum+=bean.getMoney();
                            dt.setChargeItemId(bean.getChargeId());
                            dt.setEwbNo(bean.getEwb());
                            dt.setMoney(bean.getMoney());
                            dt.setRemark(bean.getRemark());
                            receiptDetail.add(dt);
                        }
                        Receipt add = new Receipt();
                        add.setCreateTime(new Date());
                        add.setTotalMoney(sum);
                        add.setReceiveCompanyId(tmp.getReciveCmpId());
                        add.setPayType(tmp.getReciveTypeValue());
                        add.setPayCompanyId(tmp.getCmpId());
                        add.setPayName(tmp.getCmpName());
                        add.setPayCompanyType(tmp.getCmpTypeId());
                        add.setCreateTime(new Date());
                        add.setCreateBy(BaseUserUtils.getCurrentBaseUser().getId());
                        add.setStatus("1");
                        add.setReceiveTime(tmp.getReceiveDate());
                        add.setReceiptDetail(receiptDetail);
                        add.setCompanyid(tmp.getReciveCmpId());
                        receiptService.insertReceipt(add);
                        iDs.add(add.getId());
                    }
                }
            }
        }
        return iDs;
    }
    Map<String,Object> getPCmp(List<ReceiptImportBean> org){
        Map<String,Object> datas = new HashMap<String,Object>();
        for (ReceiptImportBean b:org) {
            ArrayList<Object> tmp = (ArrayList<Object>) datas.get(b.getCmpName());
            if(tmp==null){
                tmp = new ArrayList<Object>();
            }
            tmp.add(b);
            datas.put(b.getCmpName(),tmp);
        }
        return datas;
    }
    Map<String,Object> getRCmp(List<ReceiptImportBean> org){
        Map<String,Object> datas = new HashMap<String,Object>();
        for (ReceiptImportBean b:org) {
            ArrayList<Object> tmp = (ArrayList<Object>) datas.get(b.getReciveCmp());
            if(tmp==null){
                tmp = new ArrayList<Object>();
            }
            tmp.add(b);
            datas.put(b.getReciveCmp(),tmp);
        }
        return datas;
    }
    Map<String,Object> getDate(List<ReceiptImportBean> org){
        Map<String,Object> datas = new HashMap<String,Object>();
        for (ReceiptImportBean b:org) {
            String dateStr = DateUtil.formatDate(b.getReceiveDate(),"yyyy-MM-dd");
            ArrayList<Object> tmp = (ArrayList<Object>) datas.get(dateStr);
            if(tmp==null){
                tmp = new ArrayList<Object>();
            }
            tmp.add(b);
            datas.put(dateStr,tmp);
        }
        return datas;
    }
    class ReceiptCompare implements Comparator<ReceiptImportBean> {

        @Override
        public int compare(ReceiptImportBean o1, ReceiptImportBean o2) {
            if(o1.getReceiveDate().getTime()>o2.getReceiveDate().getTime()){
                return 1;
            }else if(o1.getReceiveDate().getTime()<o2.getReceiveDate().getTime()){
                return -1;
            }
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
