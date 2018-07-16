package com.demo.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.exception.RestException;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.beans.PageRequest;
import com.demo.demo.sys.entity.BusCompany;
import com.demo.demo.sys.service.IBusCompanyService;
import com.demo.demo.tools.BaseUserUtils;
import com.demo.demo.tools.Tools;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.demo.demo.response.CommonStatus.DUPLICATE_ENTRY;
import static com.demo.demo.response.CommonStatus.PARAM_EMPTY;


@RestController
@RequestMapping("company")
@Api(tags = "公司信息", description = "公司信息管理")
public class BusCompanyController extends BaseController {
    @Autowired
    IBusCompanyService busCompanyService;

    @PostMapping("getAll")
    @ResponseBody
    @ApiOperation(value = "模糊查询公司列表", notes = "根据关键字查询公司列表,search为模糊查询文本")
    public Page<BusCompany> getCompany(@RequestBody PageRequest req) throws RestException {
        if (req.getPage() != null || req.getPageSize() != null) {
            Map search = new HashMap<>();
            search.put("enable", 1);
            if(req.getMapSearch()!=null){
                search.put("id",req.getMapSearch().get("cmpId"));
            }
            if (req.getSearch() != null) {
                search.put("search", "%" + req.getSearch().trim().toLowerCase() + "%");
            }
            if(req.getId()!=null){
                search.put("siteId", req.getId());
            }
            search=Tools.trimHashMap(search);
            return busCompanyService.selectByMap(new Page<BusCompany>(req.getPage().intValue(), req.getPageSize().intValue()), search);
        } else {
            throw new RestException(PARAM_EMPTY);
        }
    }

    @PostMapping("get")
    @ResponseBody
    @ApiOperation(value = "获取公司信息", notes = "根据ID获取公司信息")
    public BusCompany getById(@RequestParam(name = "id", required = true) Integer id) throws RestException {
        BusCompany ret = busCompanyService.selectById(id);
        if (ret == null) {
            throw new RestException(PARAM_EMPTY);
        } else {
            return ret;
        }
    }

    @PostMapping("update")
    @ResponseBody
    @ApiOperation(value = "修改公司信息", notes = "根据公司实体修改公司信息")
    public RestStatus update(@RequestBody BusCompany cmp) {
        try {
            cmp.setCompanyPy(PinyinHelper.getShortPinyin(cmp.getCompanyName()).toLowerCase());
        } catch (PinyinException e) {
            cmp.setCompanyPy("");
        }
        cmp.setModifyTime(new Date());
        cmp.setModifyBy(BaseUserUtils.getCurrentBaseUser().getId());
        boolean ret = busCompanyService.updateById(cmp);
        if (ret) {
            return CommonStatus.UPDATE_OK;
        } else {
            return PARAM_EMPTY;
        }
    }

    @PostMapping("add")
    @ResponseBody
    @ApiOperation(value = "新增公司", notes = "根据公司实体新增公司信息")
    public RestStatus add(@RequestBody BusCompany cmp) {
        BusCompany busCode = busCompanyService.getCmpByCode(cmp.getCompanyCode());
        if(busCode != null){
            return CommonStatus.COMPANY_CODE_REPEAT;
        }
        try {
            cmp.setCompanyPy(PinyinHelper.getShortPinyin(cmp.getCompanyName()).toLowerCase());
        } catch (PinyinException e) {
            cmp.setCompanyPy("");
        }
        cmp.setCreateTime(new Date());
        cmp.setCreateBy(BaseUserUtils.getCurrentBaseUser().getId());
        cmp.setEnabled(1);
        try {
            boolean ret = busCompanyService.insertOrUpdate(cmp);
        } catch (DuplicateKeyException ex) {
            return DUPLICATE_ENTRY;
        }
        return CommonStatus.ADD_OK;

    }

    @GetMapping("del")
    @ResponseBody
    @ApiOperation(value = "删除公司", notes = "根据ID删除公司信息")
    public RestStatus del(@RequestParam(name = "id", required = true) Integer id) {
        boolean ret = busCompanyService.deleteById(id);
        return CommonStatus.DELETE_OK;
    }
}
