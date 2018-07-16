package com.demo.demo.bus.Import;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.demo.demo.bus.Import.base.BaseImport;
import com.demo.demo.bus.Import.bean.ReceiptImportBean;
import com.demo.demo.sys.entity.BaseDict;
import com.demo.demo.sys.entity.BusCompany;
import com.demo.demo.sys.entity.ChargeItem;
import com.demo.demo.sys.entity.bus.Site;
import com.demo.demo.sys.service.BusSiteService;
import com.demo.demo.sys.service.IBaseDictService;
import com.demo.demo.sys.service.IBusCompanyService;
import com.demo.demo.sys.service.IChargeItemService;
import com.demo.demo.tools.BaseUserUtils;
import com.demo.demo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ImportReceipt extends BaseImport {
    @Autowired
    IBaseDictService baseDictService;
    @Autowired
    IBusCompanyService busCompanyService;
    @Autowired
    IChargeItemService chargeItemService;
    @Autowired
    BusSiteService busSiteService;
    @Override
    public void doAnalyze(InputStream inputStream) throws Exception {
        List<ReceiptImportBean> infos = ExcelImportUtil.importExcel(inputStream, ReceiptImportBean.class, new ImportParams());
        errData.clear();
        data.clear();
        setCls(ReceiptImportBean.class);
        for (ReceiptImportBean bean : infos) {
            if (Tools.isFieldEmpty(bean)) {
                errData.add(bean);
                continue;
            }

            if(!BaseUserUtils.getCurrentBaseUser().getCompanyCode().equals(bean.getReciveCmpCode())&&!BaseUserUtils.getCurrentBaseUser().getCompanyCode().equals("568888"))
            {
                errData.add(bean);
                continue;
            }
            if(bean.getMoney().doubleValue()>900000){
                errData.add(bean);
                continue;
            }
            BaseDict receiveType = baseDictService.hasTypeName("pay_type", bean.getReciveType());
            if (receiveType == null) {
                errData.add(bean);
                continue;
            }
            bean.setReciveTypeValue(receiveType.getValue());
            BaseDict comType = baseDictService.hasTypeName("company_type", bean.getCmpType());
            if (comType == null) {
                errData.add(bean);
                continue;
            }
            bean.setCmpTypeId(comType.getValue());
            if (!bean.getCmpTypeId().equals("3")) {
                if(bean.getCmpCode()==null){
                    errData.add(bean);
                    continue;
                }
                if(bean.getCmpTypeId().equals("1")){
                    Map search =new HashMap();
                    search.put("siteCode",bean.getCmpCode());
                    search.put("status","running");
                    List<Site> list = busSiteService.selectSiteByMap(search);
                    if(list==null&&list.size()==0){
                        errData.add(bean);
                        continue;
                    }
                    Site _t = list.get(0);
                    if(!_t.getSiteName().equals(bean.getCmpName())){
                        errData.add(bean);
                        continue;
                    }
                    bean.setCmpId(_t.getSiteId());
                }else{
                    BusCompany company = busCompanyService.getCmpByCode(bean.getCmpCode());
                    if (company == null) {
                        errData.add(bean);
                        continue;
                    }
                    if (!company.getCompanyName().equals(bean.getCmpName())) {
                        errData.add(bean);
                        continue;
                    }
                    bean.setCmpId(company.getId());
                }
            }else{
                bean.setCmpId(9999);
            }
            BusCompany rCompany = busCompanyService.getCmpByCode(bean.getReciveCmpCode());
            if (rCompany == null) {
                errData.add(bean);
                continue;
            }
            if (!rCompany.getCompanyName().equals(bean.getReciveCmp())) {
                errData.add(bean);
                continue;
            }
            bean.setReciveCmpId(rCompany.getId());
            ChargeItem item = chargeItemService.selectByCode(bean.getChargeCode());
            if (item == null) {
                errData.add(bean);
                continue;
            }
            if (!item.getChargeItemName().equals(bean.getChargeName())) {
                errData.add(bean);
                continue;
            }
            bean.setChargeId(item.getId());
            data.add(bean);
        }

    }
}
