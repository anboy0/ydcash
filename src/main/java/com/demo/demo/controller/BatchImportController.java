package com.demo.demo.controller;

import com.demo.demo.bus.Import.bean.ReceiptImportBean;
import com.demo.demo.sys.service.IBatchImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("batch")
public class BatchImportController extends BaseController {
    @Autowired
    IBatchImportService batchImportService;
    @PostMapping("receiptConfirm")
    @ResponseBody
    public Object receiptConfirm(@RequestBody List<ReceiptImportBean> req,@RequestParam("size") Integer size) {
        List<Integer> ret = batchImportService.doImPort(req,size);
        return ret;
    }
}
