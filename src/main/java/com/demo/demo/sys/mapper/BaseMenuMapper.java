package com.demo.demo.sys.mapper;

import com.demo.demo.sys.entity.BaseMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface BaseMenuMapper {

    @Cacheable(value = "baseMenus", key = "'menu_id'")
    List<BaseMenu> getAllBaseMenu();

    List<BaseMenu> getBaseMenusByBaseUserId(Integer baseUserId);

    List<BaseMenu> baseMenuTree();

    List<Long> getBaseMenusByRid(Integer rid);

    int addMenu(BaseMenu menu);

}
