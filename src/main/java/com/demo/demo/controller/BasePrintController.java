package com.demo.demo.controller;

import com.demo.demo.bus.Export.base.ExportRespones;
import com.demo.demo.bus.Print.base.BasePrint;
import com.demo.demo.bus.Print.bean.PrintRequestBean;
import com.demo.demo.exception.ExcelException;
import com.demo.demo.tools.FreemarkerTools;
import com.demo.demo.tools.SpringContextHolder;
import com.demo.demo.tools.Tools;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.demo.demo.response.CommonStatus.*;

@RestController
@RequestMapping("print")
public class BasePrintController extends BaseController{
    @PostMapping("do")
    public Object doPrint(@RequestBody PrintRequestBean req) throws ExcelException {
        String type = req.getType();
        ArrayList<Integer> info = req.getId();
        if(Tools.isEmpty(type)){
            throw new ExcelException(PRINT_TYPE);
        }
        if(info.size()==0){
            throw new ExcelException(PRINT_EMPTY);
        }
        //List<String> list = PackageUtils.getClassName(PrintRecipt.class.getPackage().getName());
        List<String> list = SpringContextHolder.getBeansName(BasePrint.class);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith(type)) {
                try {
                    Class cls = Class.forName(list.get(i));
                    BasePrint basePrint = (BasePrint) SpringContextHolder.getBean(cls);
                    ArrayList printData = basePrint.getPrintData(info);
                    try {
                        return FreemarkerTools.getHtmlDataFromArrayList(type, printData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ExcelException(PRINT_ERROR);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new ExcelException(ExportRespones.CLASS_ERROR);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ExcelException(PRINT_EMPTY);
                }
            }
        }
        throw new ExcelException(PRINT_TYPE_NOT_SUPPORT);
    }
}
