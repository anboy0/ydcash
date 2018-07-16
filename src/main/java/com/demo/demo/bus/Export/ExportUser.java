package com.demo.demo.bus.Export;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import com.demo.demo.bus.Export.base.BaseExport;
import com.demo.demo.bus.Export.base.ExportNotice;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.service.BaseUserService;
import com.demo.demo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 用户数据导出
 */
@ExportNotice(name = "用户数据导出")
@Component
public class ExportUser extends BaseExport {

    @Autowired
    BaseUserService baseUserService;

    @Override
    public HashMap doExport(HashMap parm) {
        HashMap ret = new HashMap();
        ret.put(NormalExcelConstants.CLASS, BaseUser.class);
        parm = (HashMap) Tools.trimHashMap(parm);
        List<BaseUser> list = baseUserService.selectBaseUserByMap(parm);
        ret.put(NormalExcelConstants.DATA_LIST, list);
        return ret;
    }

}
