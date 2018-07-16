package com.demo.demo.sys.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.demo.demo.sys.entity.BaseMenu;
import com.demo.demo.sys.entity.BaseRole;
import com.demo.demo.sys.entity.BaseUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseUserMapper {
    BaseUser loadUserByUsername(String username);

    BaseUser loadUserByUsernameExit(BaseUser baseUser);

    BaseUser loadUserByUsernameAndPassWord(BaseUser user);

    List<BaseRole> getBaseRolesByBaseUserId(Long id);

    List<BaseMenu> getBaseMenusByBaseUserId(Long id);

    int addUser(BaseUser user);

    List<BaseUser> getBaseUsersByKeywords(Pagination page, BaseUser baseUser);

    int updateBaseUser(BaseUser baseUser);

    int deleteBaseRoleByBaseUserId(Long baseUserId);

    int addBaseRolesForBaseUser(@Param("baseUserId") Long baseUserId, @Param("rids") Long[] rids);

    BaseUser getBaseUserById(Long baseUserId);

    int deleteBaseUser(Long baseUserId);

    List<BaseUser> selectBaseUserByMap(Map search);

}
