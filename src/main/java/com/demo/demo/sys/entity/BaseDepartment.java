package com.demo.demo.sys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表
 */
public class BaseDepartment {
    private Long id;
    private String name;
    private Long parentId;
    private String depPath;
    private boolean enabled;
    private boolean isParent;

    private Integer result;
    private List<BaseDepartment> children = new ArrayList<>();

    public List<BaseDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<BaseDepartment> children) {
        this.children = children;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }
}
