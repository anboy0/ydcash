package com.demo.demo.sys.service.impl;

import com.demo.demo.sys.mapper.BaseMenuRoleMapper;
import com.demo.demo.sys.service.BaseMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseMenuRoleServiceImpl implements BaseMenuRoleService {

    @Autowired
    BaseMenuRoleMapper baseMenuBaseRoleMapper;

    public int updateBaseMenuRole(Long rid, Long[] mids) {
        baseMenuBaseRoleMapper.deleteBaseMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        return baseMenuBaseRoleMapper.addBaseMenu(rid, mids);
    }

}
