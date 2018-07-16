package com.demo.demo.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BaseMenuRoleMapper {
    int deleteBaseMenuByRid(@Param("rid") Long rid);

    int addBaseMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);
}
