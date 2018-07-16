package com.demo.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.demo.demo.bus.Export.base.ExportRespones;
import com.demo.demo.bus.Import.ImportReceipt;
import com.demo.demo.bus.Import.base.BaseImport;
import com.demo.demo.exception.ExcelException;
import com.demo.demo.response.ResponseData;
import com.demo.demo.tools.PackageUtils;
import com.demo.demo.tools.SpringContextHolder;
import com.demo.demo.tools.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("import")
@Api(tags = "导入服务", description = "数据导入服务")
public class BaseImportController extends BaseController {
    @RequestMapping("/upload")
    @ApiOperation(value = "导入数据", notes = "根据指定的类型导入相关的数据")
    public Object upload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws ExcelException {
        if (!file.isEmpty() && !Tools.isEmpty(type)) {
            List<String> list = SpringContextHolder.getBeansName(BaseImport.class);
            //List<String> list = PackageUtils.getClassName(ImportReceipt.class.getPackage().getName());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).endsWith(type)) {
                    try {
                        Class cls = Class.forName(list.get(i));
                        BaseImport baseImport = (BaseImport) SpringContextHolder.getBean(cls);
                        try {
                            baseImport.doAnalyze(file.getInputStream());
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new ExcelException(ExportRespones.FILE_ERROR);
                        }
                        Map ret = new HashMap<String, Object>();
                        ret.put("data", baseImport.getData());
                        ret.put("size", baseImport.getSize());
                        ret.put("errSize", baseImport.getErrSize());
                        if (baseImport.getErrSize() > 0) {
                            ExportParams  exportParams = new ExportParams();
                           // exportParams.setType(ExcelType.XSSF);
                            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, baseImport.getCls(), baseImport.getErrData());
                            if (workbook != null) {
                                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                                workbook.write(bout);
                                bout.flush();
                                getSession().setAttribute(cls.toString(), bout.toByteArray());
                                bout.close();
                                workbook.close();
                            }
                        }
                        return ret;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new ExcelException(ExportRespones.CLASS_ERROR);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ExcelException(ExportRespones.FILE_ERROR);
                    }
                }
            }
        }
        return new ResponseData(ExportRespones.FILE_ERROR);
    }

    @GetMapping("getError")
    @ApiOperation(value = "根据指定类型导入数据中数据异常项", notes = "根据指定类型下载导入环境异常数据,异常数据只缓存20分钟,前端做好提示工作!")
    public void getErrorFile(@RequestParam("type") String type) throws ExcelException {
        if (!Tools.isEmpty(type)) {
            List<String> list = PackageUtils.getClassName(ImportReceipt.class.getPackage().getName());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).endsWith(type)) {
                    try {
                        Class cls = Class.forName(list.get(i));
                        byte[] data = (byte[]) getSession().getAttribute(cls.toString());
                        if (data == null) {
                            throw new ExcelException(ExportRespones.FILE_NOT_EXIST);
                        }
                        response.setHeader("content-disposition", "attachment;filename=download.xls");
                        OutputStream out = response.getOutputStream();
                        out.write(data);
                        out.flush();
                        out.close();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new ExcelException(ExportRespones.CLASS_ERROR);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ExcelException(ExportRespones.OUT_ERROR);
                    }
                }
            }
        }
    }
}
