package com.demo.demo.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.BaseRole;
import com.demo.demo.sys.entity.BaseUser;
import com.demo.demo.sys.mapper.BaseRoleMapper;
import com.demo.demo.sys.mapper.BaseUserMapper;
import com.demo.demo.sys.service.BaseUserService;


@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    BaseUserMapper baseUserMapper;
    @Autowired
    BaseRoleMapper baseRoleMapper;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return baseUserMapper.loadUserByUsername(s);
    }

    public int baseUserReg(BaseUser baseUser,int type) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        BaseUser baseUser1 = baseUserMapper.loadUserByUsername(baseUser.getUsername());
        if (baseUser1 == null) {
            return -1;
        }
        if(type==1) {
            if (!encoder.matches(baseUser.getOldpassword(), baseUser1.getPassword())) {
                return -1;
            }
        }
        baseUser.setPassword(encoder.encode(baseUser.getPassword()));
        baseUser.setEnabled(true);
        return baseUserMapper.updateBaseUser(baseUser);
    }

    @Override
    public int addUser(BaseUser user) {
        BaseUser baseUser1 = baseUserMapper.loadUserByUsernameExit(user);
        if (baseUser1 != null) {
            return -1;
        }
        return baseUserMapper.addUser(user);
    }

    public Page<BaseUser> getBaseUsersByKeywords(Page<BaseUser> page, BaseUser baseUser) {
        List<BaseUser> baseUsers = baseUserMapper.getBaseUsersByKeywords(page, baseUser);
        for(int i=0;i<baseUsers.size();i++){
            Integer uid = baseUsers.get(i).getId();
            if(uid!=null&&uid!=0) {
                baseUsers.get(i).setBaseRoles(baseRoleMapper.queryBaseRoleListByUid(uid));
            }else{
                baseUsers.get(i).setBaseRoles(new ArrayList<BaseRole>());
            }
        }
        return page.setRecords(baseUsers);
    }

    public int updateBaseUser(BaseUser baseUser) {
        BaseUser baseUser1 = baseUserMapper.loadUserByUsernameExit(baseUser);
        if (baseUser1 != null) {
            return -1;
        }
        return baseUserMapper.updateBaseUser(baseUser);
    }

    public int updateBaseUserBaseRoles(Long baseUserId, Long[] rids) {
        int i = baseUserMapper.deleteBaseRoleByBaseUserId(baseUserId);
        return baseUserMapper.addBaseRolesForBaseUser(baseUserId, rids);
    }

    public BaseUser getBaseUserById(Long baseUserId) {
        return baseUserMapper.getBaseUserById(baseUserId);
    }

    public int deleteBaseUser(Long baseUserId) {
        return baseUserMapper.deleteBaseUser(baseUserId);
    }

    @Override
    public List<BaseUser> selectBaseUserByMap(Map search) {
        return baseUserMapper.selectBaseUserByMap(search);
    }

}
