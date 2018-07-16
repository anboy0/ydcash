package com.demo.demo.sys.mapper;

import com.demo.demo.sys.entity.BaseRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseRoleMapper {

    List<BaseRole> baseRoles(BaseRole baseRole);

    List<BaseRole> baseRolesExit(BaseRole baseRole);

    List<BaseRole> queryBaseRoleListByUid(Integer uid);

    int addNewBaseRole(BaseRole baseRole);

    int updateBaseRole(BaseRole baseRole);

    int deleteBaseRoleById(Long rid);

    int deleteBaseRoleByRoleId(Long rid);

    int deleteBaseRoleMenuByRoleId(Long rid);

}
