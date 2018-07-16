package com.demo.demo.sys.service.impl;

import com.demo.demo.sys.entity.BaseRole;
import com.demo.demo.sys.mapper.BaseRoleMapper;
import com.demo.demo.sys.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseRoleServiceImpl implements BaseRoleService {

    @Autowired
    BaseRoleMapper baseRoleMapper;

    public List<BaseRole> baseRoles(BaseRole baseRole) {
        return baseRoleMapper.baseRoles(baseRole);
    }

    @Override
    public List<BaseRole> baseRolesExit(BaseRole baseRole) {
        return baseRoleMapper.baseRolesExit(baseRole);
    }

    public int addNewBaseRole(BaseRole baseRole) {
//        if (!baseRole.getName().startsWith("ROLE_")) {
//            baseRole.setName("ROLE_" + baseRole.getName());
//        }
        return baseRoleMapper.addNewBaseRole(baseRole);
    }

    @Override
    public int updateBaseRole(BaseRole baseRole) {
        return baseRoleMapper.updateBaseRole(baseRole);
    }

    public int deleteBaseRoleById(Long rid) {
        baseRoleMapper.deleteBaseRoleByRoleId(rid);
        baseRoleMapper.deleteBaseRoleMenuByRoleId(rid);
        return baseRoleMapper.deleteBaseRoleById(rid);
    }

}
