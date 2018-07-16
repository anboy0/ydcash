package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.BaseUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;


public interface BaseUserService extends UserDetailsService {

    /**
     * @param baseUser
     * @param type(1修改2重置)
     * @return
     */
    int baseUserReg(BaseUser baseUser,int type);

    int addUser(BaseUser user);

    Page<BaseUser> getBaseUsersByKeywords(Page<BaseUser> page, BaseUser baseUser);

    int updateBaseUser(BaseUser baseUser);

    int updateBaseUserBaseRoles(Long baseUserId, Long[] rids);

    BaseUser getBaseUserById(Long baseUserId);

    int deleteBaseUser(Long baseUserId);

    List<BaseUser> selectBaseUserByMap(Map search);

}
