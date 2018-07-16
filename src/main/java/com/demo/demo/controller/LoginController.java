package com.demo.demo.controller;

import com.demo.demo.sys.beans.ErrorBean;
import com.demo.demo.sys.beans.LoginBean;
import com.demo.demo.sys.entity.SysUser;
import com.demo.demo.sys.enums.ErrorCode;
import com.demo.demo.sys.service.ISysUserService;
import com.demo.demo.sysConfig.GlobalSession;
import com.demo.demo.tools.Tools;
import com.demo.demo.tools.VerifyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Api("登录请求相关接口")
public class LoginController extends BaseController {
    @Autowired
    ISysUserService sysUserService;

    @ApiOperation(value = "用户登录", notes = "根据用户提交的用户名,密码,验证码校验登录的用户是否有效,pwdMd5值为MD5(MD(username+MD5(pwd))+verifycode)", response = ErrorBean.class)
    @PostMapping("/doLogin")
    public ErrorBean login(@RequestBody LoginBean loginBean) {
        if (!VerifyCode.checkVerify(getSession(), loginBean.getVerifyCode())) {
            return ErrorCode.VERIFY_ERROR.getResult();
        }
        Map search = new HashMap();
        search.put("userName", loginBean.getUserName());
        /*Page<SysUser> sysUser = sysUserService.selectUserPage(new Page<>(1,5),search);
        EntityWrapper<SysUser> ew = new EntityWrapper<SysUser>();
        ew.setEntity(new SysUser());
        ew.where("password={0}","123456")
                .like("nickname","guangqing")
                .ge("create_time","2017-09-21 15:50:00");
        sysUserService.selectPage(new Page<>(1,5));*/
        List<SysUser> users = sysUserService.selectByMap(search);
        if (!Tools.isEmpty(users)) {
            SysUser user = users.get(0);
            if (loginBean.getPwdMd5().equals(Tools.MD5(user.getPwd() + loginBean.getVerifyCode()))) {
                Map userInfo = new HashMap();
                userInfo.put("userName", user.getUsername());
                userInfo.put("name", user.getName());
                setSession(GlobalSession.USER_LOGIN_ID, user.getId());
                return ErrorCode.LOGIN_OK.getResult(userInfo);
            }
        }
        return ErrorCode.LOGIN_ERROR.getResult();
    }

    @ApiOperation(value = "获取验证码", notes = "访问获取验证码")
    @GetMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        VerifyCode.RendorCode(request, response);
    }

    @ApiOperation(value = "Session测试", notes = "返回Session创建时间,最近回话时间,回话保存时间", response = String.class)
    @RequestMapping("/sessionTest")
    public String test() {
        Date createTime = new Date(getSession().getCreationTime());
        Date lastAccessedTime = new Date(getSession().getLastAccessedTime());
        return "SESSION:" + getSession().getId() + "#createTime:" + createTime + "#lastAccessTime:" + lastAccessedTime + "#inactiveInterval:" + getSession().getMaxInactiveInterval();
    }

}
