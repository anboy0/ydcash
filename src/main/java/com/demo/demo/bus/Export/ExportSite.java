package com.demo.demo.bus.Export;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import com.demo.demo.bus.Export.base.BaseExport;
import com.demo.demo.bus.Export.base.ExportNotice;
import com.demo.demo.sys.entity.bus.Site;
import com.demo.demo.sys.service.BusSiteService;
import com.demo.demo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 网点数据导出
 */
@ExportNotice(name = "网点数据导出")
@Component
public class ExportSite extends BaseExport {

    @Autowired
    BusSiteService busSiteService;

    @Override
    public HashMap doExport(HashMap parm) {
        HashMap ret = new HashMap();
        ret.put(NormalExcelConstants.CLASS, Site.class);
        parm = (HashMap) Tools.trimHashMap(parm);
        List<Site> list = busSiteService.selectSiteByMap(parm);
        ret.put(NormalExcelConstants.DATA_LIST, list);
        return ret;
    }

}
