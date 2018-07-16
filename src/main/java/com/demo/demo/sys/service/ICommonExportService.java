package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.entity.bus.ReceiptExport;

import java.util.List;
import java.util.Map;

public interface ICommonExportService extends IService<ReceiptExport> {
    //Page<ReceiptExport> selectReciptByMap(Page<ReceiptExport> page, Map search);
    List<ReceiptExport> selectReciptByMap(Map search);
}
