package com.demo.demo.sys.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.demo.demo.sys.entity.bus.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusSiteMapper {

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
     * 按名称查询
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

    List<Site> getSitesByCondition(Pagination page, Site site);

    List<Site> selectSiteByMap(Map search);

}
