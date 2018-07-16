package com.demo.demo.sys.service.impl;

import com.demo.demo.sys.entity.BaseDepartment;
import com.demo.demo.sys.mapper.BaseDepartmentMapper;
import com.demo.demo.sys.service.BaseDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseDepartmentServiceImpl implements BaseDepartmentService {

    @Autowired
    BaseDepartmentMapper baseDepartmentMapper;

    public int addDep(BaseDepartment baseDepartment) {
        baseDepartment.setEnabled(true);
        baseDepartmentMapper.addDep(baseDepartment);
        return baseDepartment.getResult();
    }

    public int deleteDep(Long did) {
        BaseDepartment baseDepartment = new BaseDepartment();
        baseDepartment.setId(did);
        baseDepartmentMapper.deleteDep(baseDepartment);
        return baseDepartment.getResult();
    }

    public List<BaseDepartment> getDepByPid(Long pid) {
        return baseDepartmentMapper.getDepByPid(pid);
    }

    public List<BaseDepartment> getAllDeps() {
        return baseDepartmentMapper.getAllDeps();
    }

}
