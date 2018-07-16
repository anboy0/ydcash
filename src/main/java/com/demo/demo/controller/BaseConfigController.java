package com.demo.demo.controller;

import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.entity.BaseMenu;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.service.BaseMenuService;
import com.demo.demo.tools.BaseUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base/config")
@Api(tags = "基础信息", description = "基础信息API")
public class BaseConfigController {
    @Autowired
    BaseMenuService baseMenuService;

    @PostMapping("/sysbaseMenu")
    @ApiOperation(value = "获取菜单", notes = "根据用户角色返回对应菜单")
    public List<BaseMenu> sysbaseMenu() {
        return baseMenuService.getBaseMenusByBaseUserId();
    }

    @GetMapping("/baseUser")
    @ApiOperation(value = "获取用户", notes = "获取当前登录用户")
    public BaseUser currentUser() {
        return BaseUserUtils.getCurrentBaseUser();
    }

    @PostMapping("/addMenu")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    public RestStatus addMenu(@RequestBody BaseMenu menu) {
        baseMenuService.addMenu(menu);
        return CommonStatus.ADD_OK;
    }

    @GetMapping("/login_p")
    public RestStatus login_p(){
        return CommonStatus.FORBIDDEN;
    }

}
