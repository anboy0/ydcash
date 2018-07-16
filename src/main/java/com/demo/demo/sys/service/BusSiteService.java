package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.bus.Site;

import java.util.List;
import java.util.Map;

public interface BusSiteService {

    /**
     * 按siteId查询
     * @param siteId
     * @return
     */
    public Site getSiteId(Integer siteId);

    /**
     * 按编号查询
     * @param siteCode
     * @return
     */
    public Site getSiteCode(String siteCode);

    /**
     * 按网点名查询
     * @param siteName
     * @return
     */
    public Site getSiteName(String siteName);

    /**
     * 新增网点信息
     * @param site
     * @return
     */
    int addBusSite(Site site);

    List<Site> getSitesByNameOrCode(String par);

    Page<Site> getSitesByCondition(Page<Site> page, Site site);

    List<Site> selectSiteByMap(Map search);

}
