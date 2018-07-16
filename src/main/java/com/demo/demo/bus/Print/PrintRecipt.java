package com.demo.demo.bus.Print;

import com.demo.demo.bus.Print.base.BasePrint;
import com.demo.demo.sys.service.IReceiptService;
import com.demo.demo.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class PrintRecipt extends BasePrint {
    @Autowired
    IReceiptService receiptService;
    @Override
    public ArrayList<Object> getPrintData(ArrayList datas) throws Exception{

        Map search = new HashMap();
        search.put("list",datas);
        List<Map<String,Object>> info = receiptService.selectReceiptDetailByIds(search);
        if(info==null){
            throw new Exception();
        }
        ArrayList<Object> ret = new ArrayList<Object>();
        Map<String,Object> sn =getSnList(info);
        Date printDate = new Date();
        for(Map.Entry<String,Object > entry:sn.entrySet()){
            Map detail = new HashMap();
            detail.put("prop","收费");
            ArrayList<Map<String,Object>> dt = (ArrayList<Map<String, Object>>) entry.getValue();
            Map<String,Object> tmp = dt.get(0);
            detail.put("rCmp",tmp.get("rCmp")==null?"":tmp.get("rCmp"));
            detail.put("type",tmp.get("name")==null?"":tmp.get("name"));
            detail.put("pCmp",tmp.get("pCmp")==null?"":tmp.get("pCmp"));
            detail.put("receiveDate",tmp.get("createTime")==null?"": DateUtil.formatDate((Date) tmp.get("createTime"),"yyyy-MM-dd"));
            detail.put("optDate",tmp.get("receiveTime")==null?"": DateUtil.formatDate((Date) tmp.get("receiveTime"),"yyyy-MM-dd"));
            detail.put("printDate",DateUtil.formatDate(printDate,"yyyy-MM-dd HH:mm:ss") );
            detail.put("total",tmp.get("tMoney")==null?"":getTotalCashFormat(tmp.get("tMoney")) );
            detail.put("sn", tmp.get("sn")==null?"":tmp.get("sn"));
            detail.put("optName",tmp.get("userName")==null?"":tmp.get("userName"));
            int i = 0;
            for(; i< dt.size();i++){
                tmp = dt.get(i);
                int y = i+1;
                detail.put("no"+y,y);
                detail.put("name"+y,tmp.get("chargeName")==null?"":tmp.get("chargeName"));
                detail.put("data"+y,tmp.get("money")==null?"":getCashFormat(tmp.get("money")));
                detail.put("ewb"+y,tmp.get("ewbNo")==null?"":getEwbNo(tmp.get("ewbNo")));
                detail.put("remark"+y,tmp.get("remark")==null?"":tmp.get("remark"));
            }
            i++;
            if(dt.size()<5){
                for(;i<6;i++){
                    detail.put("no"+i,"　");
                    detail.put("name"+i,"　");
                    detail.put("data"+i,"　");
                    detail.put("ewb"+i,"　");
                    detail.put("remark"+i,"　");
                }
            }
            ret.add(detail);
        }
        Map update = new HashMap();
        ret.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map info1 = (Map) o1;
                Map info2 = (Map) o2;
                String str1 = (String) info1.get("sn");
                String str2 = (String) info2.get("sn");
                if(str1.compareTo(str2)<0){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        update.put("list",datas);
        update.put("printTime",printDate);
        receiptService.updatePrint(update);
        System.out.println(sn.size());

        return ret;
    }

    String getCashFormat(Object money){
        if(money==null)
            return " ";
        if(money instanceof BigDecimal) {
            BigDecimal m = (BigDecimal) money;
            double info = m.doubleValue();
            if(info>1000000.00){
                return "1000000.00";
            }
            return String.format("%.2f", info);
        }else
            return "";
    }
    String getTotalCashFormat(Object money){
        if(money==null)
            return " ";
        if(money instanceof BigDecimal) {
            BigDecimal m = (BigDecimal) money;
            double info = m.doubleValue();

            return String.format("%.2f", info);
        }else
            return "";
    }
    String getEwbNo(Object ewb){
        if(ewb instanceof String){
            String str = (String) ewb;
            if(str.length()>9){
                return str.substring(0,9);
            }else{
                return str;
            }
        }else{
            return "";
        }
    }

    Map<String,Object> getSnList(List<Map<String,Object>> info){
        Map<String,Object> datas = new HashMap<String,Object>();
        for (Map<String,Object> l:info) {
            ArrayList<Object> tmp = (ArrayList<Object>) datas.get(l.get("sn"));
            if(tmp==null){
                tmp = new ArrayList<Object>();
            }
            tmp.add(l);
            datas.put((String) l.get("sn"),tmp);
        }
        return datas;
    }
}
