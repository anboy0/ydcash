package com.demo.demo.controller;


import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.beans.PageRequest;
import com.demo.demo.sys.entity.BaseDict;
import com.demo.demo.sys.service.IBaseDictService;
import com.demo.demo.tools.BaseUserUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.demo.demo.response.CommonStatus.ADD_OK;

/**
 * <p>
 * 系统字典表 前端控制器
 * </p>
 *
 * @author cgm123
 * @since 2018-01-17
 */
@Controller
@RequestMapping("/baseDict")
@Api(tags = "字典表", description = "系统字典表")
public class BaseDictController {
    @Autowired
    IBaseDictService baseDictService;

    @PostMapping("/get")
    @ResponseBody
    public Object getDict(@RequestBody PageRequest request
    ) {
        HashMap search = new HashMap();
        search.put("status", request.getStatus());
        search.put("type_name", request.getType());
        List<BaseDict> pages = baseDictService.selectByMap(search);
        return pages;
    }

    @PostMapping("/add")
    @ResponseBody
    public RestStatus add(@RequestBody BaseDict request
    ) {
        if (request.getId() != null) {
            request.setModifyTime(new Date());
            request.setModifyBy(BaseUserUtils.getCurrentBaseUser().getId());
        } else {
            request.setCreateBy(BaseUserUtils.getCurrentBaseUser().getId());
            request.setCreateTime(new Date());
        }
        baseDictService.insertOrUpdate(request);
        return ADD_OK;
    }

    @PostMapping("/update")
    @ResponseBody
    public RestStatus update(@RequestBody BaseDict request
    ) {
        request.setModifyTime(new Date());
        request.setModifyBy(BaseUserUtils.getCurrentBaseUser().getId());
        baseDictService.updateById(request);
        return ADD_OK;
    }

    @PostMapping("/del")
    @ResponseBody
    public RestStatus del(@RequestParam("id") Integer id
    ) {
        baseDictService.deleteById(id);
        return ADD_OK;
    }
}

