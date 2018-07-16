package com.demo.demo.bus.Export;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import com.demo.demo.bus.Export.base.BaseExport;
import com.demo.demo.bus.Export.base.ExportNotice;
import com.demo.demo.sys.entity.bus.ReceiptExport;
import com.demo.demo.sys.service.ICommonExportService;
import com.demo.demo.sys.service.ISysUserService;
import com.demo.demo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 收款单据导出
 */
@ExportNotice(name = "收款单据导出")
@Component
public class ExportReceipt extends BaseExport {
    @Autowired
    ICommonExportService commonExportService;
    @Autowired
    ISysUserService sysUserService;

    @Override
    public HashMap doExport(HashMap parm) {
        HashMap ret = new HashMap();
        ret.put(NormalExcelConstants.CLASS, ReceiptExport.class);
        /*Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.where("status={0}",1);
        List<SysUser> users = sysUserService.selectList(wrapper);*/
        /*if(parm.get("startTime")!=null){
            try {
                parm.put("startTime", DateUtil.getStrToDate((String) parm.get("startTime")));
            } catch (ParseException e) {
                parm.put("startTime",null);
                e.printStackTrace();
            }
        }
        if(parm.get("endTime")!=null){
            try {
                parm.put("endTime", DateUtil.getStrToDate((String) parm.get("endTime")));
            } catch (ParseException e) {
                parm.put("endTime",null);
                e.printStackTrace();
            }
        }*/
        parm = (HashMap) Tools.trimHashMap(parm);
        List<ReceiptExport> list = commonExportService.selectReciptByMap(parm);
        ret.put(NormalExcelConstants.DATA_LIST, list);
        return ret;
    }

}
