package com.demo.demo.controller;

import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.bus.Site;
import com.demo.demo.sys.service.BusSiteService;

@RestController
@RequestMapping("/bus/site")
@Api(tags = "网点管理", description = "网点管理API")
public class BusSiteController {

    @Autowired
    BusSiteService busSiteService;

    @GetMapping("/getSitesByNameOrCode")
    @ApiOperation(value = "模糊查询网点", notes = "根据参数模糊查询网点（名称或编号）")
    public List<Site> getSitesByNameOrCode(String par) {
        return busSiteService.getSitesByNameOrCode(par);
    }

    @PostMapping("/getSitesByCondition")
    @ApiOperation(value = "查网点集合", notes = "根据参数查网点集合")
    public Page<Site> getSitesByCondition(@RequestBody Site site) {
        return busSiteService.getSitesByCondition(new Page<>(site.getPage(), site.getPageSize()), site);
    }

    @PostMapping(value = "/addBusSite")
    @ApiOperation(value = "新增网点管理", notes = "新增网点管理")
    public RestStatus addUser(@RequestBody Site siteVO) {
        if(siteVO != null){
            Site obj = busSiteService.getSiteCode(siteVO.getSiteCode());
            Site ob = busSiteService.getSiteName(siteVO.getSiteName());
            if(obj != null){
                return CommonStatus.SITE_CODE_REPEAT;
            }
            if(ob != null){
                return CommonStatus.SITE_NAME_REPEAT;
            }
            //所属网点ID
            Site parentSiteId = busSiteService.getSiteId(siteVO.getParent());
            if(parentSiteId != null){
                siteVO.setParent(parentSiteId.getSiteId());
            } else {
                return CommonStatus.SITE_NO_PARENT_REPEAT;
            }
            siteVO.setCreateTime(new Date());
            siteVO.setCreateBy(25L);
        } else {
            return CommonStatus.PARAM_EMPTY;
        }
        try {
            busSiteService.addBusSite(siteVO);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonStatus.ADD_NO_OK;
        }
        return CommonStatus.SAVE_OK;
    }

}
