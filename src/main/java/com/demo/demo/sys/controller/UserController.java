package com.demo.demo.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.controller.BaseController;
import com.demo.demo.sys.beans.ErrorBean;
import com.demo.demo.sys.entity.SysUser;
import com.demo.demo.sys.enums.ErrorCode;
import com.demo.demo.sys.service.ISysUserService;
import com.demo.demo.tools.FreemarkerTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    ISysUserService sysUserService;

    //添加缓存
    @Cacheable(value = "UserController", key = "'user:'+#id")
    @RequestMapping("/get")
    public Object modifyUser(@RequestParam Long id) {
        SysUser user = sysUserService.selectById(id);
        return user;
    }

    //清除缓存
    @CacheEvict(value = "UserController", key = "'user:'+#id")
    @RequestMapping("/del")
    public String clearUser(@RequestParam Long id) {
        return "clear cache";
    }

    //更新缓存
    @CachePut(value = "UserController", key = "'user:'+#id")
    @RequestMapping("/update")
    public Object updateUser(@RequestParam Long id) {
        SysUser user = sysUserService.selectById(id);
        user.setUsername(user.getUsername() + "#update");
        return user;
    }

    @RequestMapping("/print")
    public ErrorBean getPrint() {
        ErrorBean errorBean;
        HashMap printData = new HashMap();
        printData.put("project", "打印测试");
        printData.put("no", "1");
        printData.put("desc", "这是个测试");
        printData.put("test", "testMeTestME");
        Page<SysUser> user = sysUserService.selectPage(new Page<SysUser>(1, 20));
        ArrayList al = new ArrayList();
        al.add(user.getRecords());
        al.add(user.getRecords());
        //printData.put("users",user.getRecords());


        try {
            errorBean = ErrorCode.ACCESS_OK.getResult(FreemarkerTools.getHtmlDataFromArrayList("test", al));
        } catch (Exception e) {
            e.printStackTrace();
            errorBean = ErrorCode.ACCESS_ERROR_PRINT.getResult();
        }

        return errorBean;
    }
}
