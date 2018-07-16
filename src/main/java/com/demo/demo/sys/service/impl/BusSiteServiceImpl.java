package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.bus.Site;
import com.demo.demo.sys.mapper.BusSiteMapper;
import com.demo.demo.sys.service.BusSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusSiteServiceImpl implements BusSiteService {

    @Autowired
    private BusSiteMapper busSiteMapper;

    @Override
    public Site getSiteId(Integer siteId){
        return busSiteMapper.getSiteId(siteId);
    }

    @Override
    public Site getSiteCode(String siteCode){
        return busSiteMapper.getSiteCode(siteCode);
    }

    @Override
    public Site getSiteName(String siteName){
        return busSiteMapper.getSiteName(siteName);
    }

    @Override
    public int addBusSite(Site site) {
        return busSiteMapper.addBusSite(site);
    }

    @Override
    public List<Site> getSitesByNameOrCode(String par) {
        return busSiteMapper.getSitesByNameOrCode(par);
    }

    @Override
    public Page<Site> getSitesByCondition(Page<Site> page, Site site) {
        return page.setRecords(busSiteMapper.getSitesByCondition(page, site));
    }

    @Override
    public List<Site> selectSiteByMap(Map search) {
        return busSiteMapper.selectSiteByMap(search);
    }

}
