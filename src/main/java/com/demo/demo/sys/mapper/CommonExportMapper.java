package com.demo.demo.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.demo.sys.entity.bus.ReceiptExport;

import java.util.List;
import java.util.Map;

public interface CommonExportMapper extends BaseMapper<ReceiptExport> {
    //List<ReceiptExport> selectReciptByMap(Pagination page, Map search);
    List<ReceiptExport> selectReciptByMap(Map search);
}
