package com.demo.demo.sys.service;

import com.demo.demo.sys.entity.BaseRole;

import java.util.List;

public interface BaseRoleService {

    List<BaseRole> baseRoles(BaseRole baseRole);

    List<BaseRole> baseRolesExit(BaseRole baseRole);

    int addNewBaseRole(BaseRole baseRole);

    int updateBaseRole(BaseRole baseRole);

    int deleteBaseRoleById(Long rid);

}
