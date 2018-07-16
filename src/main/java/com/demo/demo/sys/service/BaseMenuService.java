package com.demo.demo.sys.service;

import com.demo.demo.sys.entity.BaseMenu;

import java.util.List;

public interface BaseMenuService {

    List<BaseMenu> getAllBaseMenu();

    List<BaseMenu> getBaseMenusByBaseUserId();

    List<BaseMenu> baseMenuTree();

    List<Long> getBaseMenusByRid(Integer rid);

    int addMenu(BaseMenu menu);

}
