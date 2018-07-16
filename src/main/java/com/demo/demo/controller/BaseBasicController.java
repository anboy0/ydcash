package com.demo.demo.controller;

import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.entity.BaseDepartment;
import com.demo.demo.sys.entity.BaseMenu;
import com.demo.demo.sys.entity.BaseRole;
import com.demo.demo.sys.service.BaseDepartmentService;
import com.demo.demo.sys.service.BaseMenuRoleService;
import com.demo.demo.sys.service.BaseMenuService;
import com.demo.demo.sys.service.BaseRoleService;
import com.demo.demo.tools.BaseUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/base/basic")
@Api(tags = "菜单角色相关设置", description = "菜单角色相关设置API")
public class BaseBasicController {
    @Autowired
    BaseRoleService baseRoleService;
    @Autowired
    BaseMenuService baseMenuService;
    @Autowired
    BaseMenuRoleService baseMenuBaseRoleService;
    @Autowired
    BaseDepartmentService baseDepartmentService;

    @PostMapping(value = "/deleteBaseRole")
    @ApiOperation(value = "删除角色", notes = "根据角色Id删除角色")
    public RestStatus deleteBaseRole(@RequestBody Long rid) {
        try {
            baseRoleService.deleteBaseRoleById(rid);
        } catch (Exception e) {
            return CommonStatus.ROLE_HAVE_MENU;
        }
        return CommonStatus.DELETE_OK;
    }

    @PostMapping(value = "/addNewBaseRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public RestStatus addNewBaseRole(@RequestBody BaseRole baseRole) {
        BaseRole baseRole1 = new BaseRole();
        BaseRole baseRole2 = new BaseRole();
        baseRole1.setNameZh(baseRole.getNameZh());
        baseRole2.setName(baseRole.getName());
        if(baseRoleService.baseRolesExit(baseRole1).size()>0){
            return CommonStatus.ROLE_NAME_REPEAT;
        }
        if(baseRoleService.baseRolesExit(baseRole2).size()>0){
            return CommonStatus.ROLE_NAME_REPEAT;
        }
        baseRole.setCreateBy(BaseUserUtils.getCurrentBaseUser().getId().longValue());
        baseRole.setCreateTime(new Date());
        baseRoleService.addNewBaseRole(baseRole);
        return CommonStatus.ADD_OK;
    }

    @PostMapping(value = "/updateBaseRole")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public RestStatus updateBaseRole(@RequestBody BaseRole baseRole) {
        BaseRole baseRole1 = new BaseRole();
        BaseRole baseRole2 = new BaseRole();
        baseRole1.setId(baseRole.getId());
        baseRole1.setNameZh(baseRole.getNameZh());
        baseRole2.setId(baseRole.getId());
        baseRole2.setName(baseRole.getName());
        if(baseRoleService.baseRolesExit(baseRole1).size()>0){
            return CommonStatus.ROLE_NAME_REPEAT;
        }
        if(baseRoleService.baseRolesExit(baseRole2).size()>0){
            return CommonStatus.ROLE_NAME_REPEAT;
        }
        baseRole.setModifyBy(BaseUserUtils.getCurrentBaseUser().getId().longValue());
        baseRole.setModifyTime(new Date());
        baseRoleService.updateBaseRole(baseRole);
        return CommonStatus.UPDATE_OK;
    }

    @PostMapping(value = "/baseMenuTree")
    @ApiOperation(value = "获取菜单", notes = "根据角色Id获取角色对应菜单")
    public Map<String, Object> baseMenuTree(@RequestBody Integer rid) {
        Map<String, Object> map = new HashMap<>();
        List<BaseMenu> baseMenus = baseMenuService.baseMenuTree();
        map.put("baseMenus", baseMenus);
        List<Long> selMids = baseMenuService.getBaseMenusByRid(rid);
        map.put("mids", selMids);
        return map;
    }

    @PostMapping(value = "/updateBaseMenuRole")
    @ApiOperation(value = "角色添加权限", notes = "给角色添加权限/菜单")
    public RestStatus updateBaseMenuRole(@RequestBody BaseRole baseRole) {
        baseMenuBaseRoleService.updateBaseMenuRole(baseRole.getId().longValue(), baseRole.getMids());
        return CommonStatus.ADD_OK;
    }

    @PostMapping("/allBaseRoles")
    @ApiOperation(value = "查角色列表", notes = "查询所有角色列表")
    public List<BaseRole> allBaseRoles(@RequestBody BaseRole baseRole) {
        return baseRoleService.baseRoles(baseRole);
    }

    @PostMapping(value = "/addDep")
    @ApiOperation(value = "新增部门", notes = "新增部门")
    public Map<String, Object> addDep(@RequestBody BaseDepartment baseDepartment) {
        Map<String, Object> map = new HashMap<>();
        if (baseDepartmentService.addDep(baseDepartment) == 1) {
            map.put("status", "success");
            map.put("msg", baseDepartment);
            return map;
        }
        map.put("status", "error");
        map.put("msg", "添加失败!");
        return map;
    }

    @PostMapping(value = "/deleteDep")
    @ApiOperation(value = "删除部门", notes = "根据部门Id删除部门")
    public RestStatus deleteDep(@RequestBody Long did) {
        baseDepartmentService.deleteDep(did);
        return CommonStatus.DELETE_OK;
    }

    @PostMapping(value = "/getDepByPid")
    @ApiOperation(value = "查部门", notes = "根据部门Id查询")
    public List<BaseDepartment> getDepByPid(@RequestBody Long pid) {
        return baseDepartmentService.getDepByPid(pid);
    }

    @PostMapping(value = "/getAllDeps")
    @ApiOperation(value = "查部门列表", notes = "查部门列表")
    public List<BaseDepartment> getAllDeps() {
        return baseDepartmentService.getAllDeps();
    }

}
