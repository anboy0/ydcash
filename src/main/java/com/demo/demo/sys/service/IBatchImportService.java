package com.demo.demo.sys.service;

import com.demo.demo.bus.Import.bean.ReceiptImportBean;

import java.util.List;
public interface IBatchImportService {
    List<Integer> doImPort(List<ReceiptImportBean> org,int size);
}
