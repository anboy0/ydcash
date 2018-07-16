package com.demo.demo.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "菜单表")
public class BaseMenu extends CommonVo implements Serializable {

    private static final long serialVersionUID = 516853166487055381L;

    @ApiModelProperty(name = "url", value = "全路径")
    private String url;
    @ApiModelProperty(name = "path", value = "路径")
    private String path;
    @ApiModelProperty(name = "component", value = "描述")
    private Object component;
    @ApiModelProperty(name = "name", value = "名称")
    private String name;
    @ApiModelProperty(name = "iconCls", value = "图标")
    private String iconCls;
    @ApiModelProperty(name = "parentId", value = "父节点Id")
    private Long parentId;
    @ApiModelProperty(name = "baseRoles", value = "对应角色")
    private List<BaseRole> baseRoles;
    @ApiModelProperty(name = "children", value = "下级菜单")
    private List<BaseMenu> children;
    @ApiModelProperty(name = "meta", value = "额外属性")
    private BaseMenuMeta meta;
    private boolean requireAuth;
    private boolean keepAlive;

    public BaseMenuMeta getMeta() {
        return meta;
    }

    public void setMeta(BaseMenuMeta meta) {
        this.meta = meta;
    }

    public List<BaseMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BaseMenu> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getComponent() {
        return component;
    }

    public void setComponent(Object component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<BaseRole> getBaseRoles() {
        return baseRoles;
    }

    public void setBaseRoles(List<BaseRole> baseRoles) {
        this.baseRoles = baseRoles;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
