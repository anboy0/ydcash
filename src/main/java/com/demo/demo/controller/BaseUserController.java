package com.demo.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.service.BaseUserService;
import com.demo.demo.tools.BaseUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/base/baseUser")
@Api(tags = "用户管理", description = "用户管理相关API")
public class BaseUserController {
    @Autowired
    BaseUserService baseUserService;

    @Autowired
    SessionRegistry sessionRegistry;

    @PostMapping("/getBaseUserById")
    @ApiOperation(value = "获取用户", notes = "根据用户Id获取一个用户")
    public BaseUser getBaseUserById(@RequestBody Long baseUserId) {
        return baseUserService.getBaseUserById(baseUserId);
    }

    @PostMapping("/deleteBaseUser")
    @ApiOperation(value = "删除用户", notes = "根据用户Id删除用户")
    public RestStatus deleteBaseUser(@RequestBody Long[] baseUserId) {
        BaseUser baseUser;
        for(Long va:baseUserId){
            baseUser = baseUserService.getBaseUserById(va);
            baseUser.setEnabled(false);
            baseUser.setIsdel(true);
            baseUserService.updateBaseUser(baseUser);
            invalidateSession(baseUser);
        }
        return CommonStatus.DELETE_OK;
    }

    @PostMapping(value = "/updateBaseUser")
    @ApiOperation(value = "修改用户", notes = "根据用户Id修改用户")
    public RestStatus updateBaseUser(@RequestBody List<BaseUser> baseUsers) {
        for(BaseUser baseUser:baseUsers){
            baseUser.setModifyTime(new Date());
            baseUser.setModifyBy(BaseUserUtils.getCurrentBaseUser().getId().longValue());
            if(baseUserService.updateBaseUser(baseUser)!=-1){
                updateBaseUserBaseRoles(baseUser);
                if(!baseUser.isEnabled()){
                    invalidateSession(baseUser);
                }
            }else{
                return CommonStatus.USER_NAME_REPEAT;
            }
        }
        return CommonStatus.UPDATE_OK;
    }

    @PostMapping(value = "/updateBaseUserBaseRoles")
    @ApiOperation(value = "用户分配角色", notes = "用户分配角色（新增修改都适用）")
    public RestStatus updateBaseUserBaseRoles(@RequestBody BaseUser user) {
        baseUserService.updateBaseUserBaseRoles(user.getId().longValue(), user.getRids());
        return CommonStatus.UPDATE_OK;
    }

    @PostMapping("/getBaseUsersByKeywords")
    @ApiOperation(value = "查询用户", notes = "根据条件查询用户列表")
    public Page<BaseUser> getBaseUsersByKeywords(@RequestBody BaseUser baseUser) {
        return baseUserService.getBaseUsersByKeywords(new Page<>(baseUser.getPage(), baseUser.getPageSize()), baseUser);
    }

    @PostMapping(value = "/updatePassWord")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public RestStatus updatePassWord(@RequestBody BaseUser user) {
        if(baseUserService.baseUserReg(user,1)==-1){
            return CommonStatus.UNAMEORPWORD_NOTEXSIT;
        }
        return CommonStatus.UPDATE_OK;
    }

    @PostMapping(value = "/resetPassWord")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public RestStatus resetPassWord(@RequestBody BaseUser user) {
        user.setPassword("955460");
        if(baseUserService.baseUserReg(user,2)==-1){
            return CommonStatus.UNAMEORPWORD_NOTEXSIT;
        }
        return CommonStatus.UPDATE_OK;
    }

    @PostMapping(value = "/addUser")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public RestStatus addUser(@RequestBody BaseUser user) {
        user.setCreateTime(new Date());
        user.setCreateBy(BaseUserUtils.getCurrentBaseUser().getId().longValue());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("955460");
        user.setPassword(encode);
        if(baseUserService.addUser(user)!=-1) {
            updateBaseUserBaseRoles(user);
        }else{
            return CommonStatus.USER_NAME_REPEAT;
        }
        return CommonStatus.SAVE_OK;
    }

    /**
     * 清session
     * @param user
     */
    private void invalidateSession(BaseUser user){
        List<Object> o= sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            if (principal instanceof BaseUser) {
                final BaseUser loggedUser = (BaseUser) principal;
                if (user.getUsername().equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                }
            }
        }
    }

}
