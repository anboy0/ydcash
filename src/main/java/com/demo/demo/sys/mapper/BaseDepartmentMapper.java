package com.demo.demo.sys.mapper;

import com.demo.demo.sys.entity.BaseDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseDepartmentMapper {
    void addDep(@Param("dep") BaseDepartment baseDepartment);

    void deleteDep(@Param("dep") BaseDepartment baseDepartment);

    List<BaseDepartment> getDepByPid(Long pid);

    List<BaseDepartment> getAllDeps();
}
