package com.demo.demo.sys.service;

import com.demo.demo.sys.entity.BaseDepartment;

import java.util.List;

public interface BaseDepartmentService {

    int addDep(BaseDepartment baseDepartment);

    int deleteDep(Long did);

    List<BaseDepartment> getDepByPid(Long pid);

    List<BaseDepartment> getAllDeps();

}
