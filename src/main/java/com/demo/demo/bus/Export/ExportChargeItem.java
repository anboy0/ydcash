package com.demo.demo.bus.Export;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;

import com.demo.demo.bus.Export.base.BaseExport;
import com.demo.demo.bus.Export.base.ExportNotice;
import com.demo.demo.sys.entity.ChargeItemExport;
import com.demo.demo.sys.service.IChargeItemService;
import com.demo.demo.tools.Tools;

/**
 * 费用项目导出
 */
@ExportNotice(name = "费用项目导出")
@Component
public class ExportChargeItem extends BaseExport {
    @Autowired
    IChargeItemService chargeItemService;

    @Override
    public HashMap doExport(HashMap parm) {
        HashMap ret = new HashMap();
        ret.put(NormalExcelConstants.CLASS, ChargeItemExport.class);
        parm = (HashMap) Tools.trimHashMap(parm);
        List<ChargeItemExport> list = chargeItemService.selectChargeItemByMap(parm);
        ret.put(NormalExcelConstants.DATA_LIST, list);
        return ret;
    }

}
