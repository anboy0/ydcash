package com.demo.demo.sys.service.impl;

import com.demo.demo.sys.entity.BaseMenu;
import com.demo.demo.sys.mapper.BaseMenuMapper;
import com.demo.demo.sys.service.BaseMenuService;
import com.demo.demo.tools.BaseUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseMenuServiceImpl implements BaseMenuService {

    @Autowired
    BaseMenuMapper baseMenuMapper;

    public List<BaseMenu> getAllBaseMenu() {
        return baseMenuMapper.getAllBaseMenu();
    }

    public List<BaseMenu> getBaseMenusByBaseUserId() {
        return baseMenuMapper.getBaseMenusByBaseUserId(BaseUserUtils.getCurrentBaseUser().getId());
    }

    public List<BaseMenu> baseMenuTree() {
        return baseMenuMapper.baseMenuTree();
    }

    public List<Long> getBaseMenusByRid(Integer rid) {
        return baseMenuMapper.getBaseMenusByRid(rid);
    }

    public int addMenu(BaseMenu menu) {
        return baseMenuMapper.addMenu(menu);
    }

}
