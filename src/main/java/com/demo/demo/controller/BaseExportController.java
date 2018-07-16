package com.demo.demo.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.alibaba.fastjson.JSON;
import com.demo.demo.bus.Export.ExportReceipt;
import com.demo.demo.bus.Export.base.BaseExport;
import com.demo.demo.bus.Export.base.ExportNotice;
import com.demo.demo.bus.Export.base.ExportRespones;
import com.demo.demo.exception.ExcelException;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.beans.PageRequest;
import com.demo.demo.sys.service.ISysUserService;
import com.demo.demo.tools.DateUtil;
import com.demo.demo.tools.PackageUtils;
import com.demo.demo.tools.SpringContextHolder;
import com.demo.demo.tools.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@RestController
@RequestMapping("export")
@Api(tags = "导出服务", description = "数据导出服务")
public class BaseExportController extends BaseController {
    @Autowired
    ISysUserService sysUserService;

    @GetMapping("do")
    @ApiOperation(value = "导出数据", notes = "根据指定的类型导出相关的数据")
    public RestStatus getExport(@RequestParam("data") String data) throws ExcelException {
        String str = null;
        try {
            str = URLDecoder.decode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ExportRespones.CLASS_ERROR;
        }
        PageRequest req = JSON.parseObject(str,PageRequest.class);
        if(req==null){
            return ExportRespones.CLASS_ERROR;
        }
        if (!Tools.isEmpty(req.getType())) {
            //List<String> list = PackageUtils.getClassName(ExportReceipt.class.getPackage().getName());
            List<String> list = SpringContextHolder.getBeansName(BaseExport.class);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).endsWith(req.getType())) {
                    try {
                        Class cls = Class.forName(list.get(i));
                        BaseExport export = (BaseExport) SpringContextHolder.getBean(cls);
                        HashMap ret = export.doExport(req.getMapSearch());
                        if (ret == null || ret.size() == 0) {
                            throw new ExcelException(ExportRespones.EMPTY_ERROR);
                        }
                        ret.put(NormalExcelConstants.PARAMS, new ExportParams());
                        ExportNotice notice = (ExportNotice) cls.getAnnotation(ExportNotice.class);
                        if (notice != null) {
                            ret.put(NormalExcelConstants.FILE_NAME, notice.name() + "_" + DateUtil.formatDate(new Date(), "yyMMddHHmmss"));
                        } else {
                            ret.put(NormalExcelConstants.FILE_NAME, "数据导出");
                        }
                        PoiBaseView.render(ret, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
                        try {
                            response.getOutputStream().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new ExcelException(ExportRespones.CLASS_ERROR);
                    }
                    break;
                }

            }
        }
        return ExportRespones.CLASS_ERROR;
    }

    @GetMapping("support")
    @ResponseBody
    @ApiOperation(value = "获取支持导出的数据类型", notes = "获取支持导出的数据类型")
    public Object getExportList() {
        List<Object> rets = new ArrayList<>();
        List<String> list = PackageUtils.getClassName(ExportReceipt.class.getPackage().getName());
        for (int i = 0; i < list.size(); i++) {
            try {
                Map item = new HashMap<>();
                Class cls = Class.forName(list.get(i));
                ExportNotice notice = (ExportNotice) cls.getAnnotation(ExportNotice.class);
                item.put("method", PackageUtils.getClassShortName(list.get(i)));
                if (notice != null) {
                    item.put("desc", notice.name());
                }
                rets.add(item);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return rets;
    }
}
