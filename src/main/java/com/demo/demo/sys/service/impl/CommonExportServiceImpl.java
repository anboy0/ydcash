package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.entity.bus.ReceiptExport;
import com.demo.demo.sys.mapper.CommonExportMapper;
import com.demo.demo.sys.service.ICommonExportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonExportServiceImpl extends ServiceImpl<CommonExportMapper, ReceiptExport> implements ICommonExportService {
   /* @Override
    public Page<ReceiptExport> selectReciptByMap(Page<ReceiptExport> page, Map search) {
        return null;
    }*/

    @Override
    public List<ReceiptExport> selectReciptByMap(Map search) {
        return baseMapper.selectReciptByMap(search);
    }
}
