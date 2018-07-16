package com.demo.demo.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "角色/岗位表")
public class BaseRole extends CommonVo implements Serializable {

    private static final long serialVersionUID = -460039686624564974L;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;
    @ApiModelProperty(name = "nameZh", value = "中文名")
    private String nameZh;
    @ApiModelProperty(name = "menuName", value = "菜单名称")
    private String menuName;
    private Long[] mids;
    private String createName;
    private String modifyName;

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long[] getMids() {
        return mids;
    }

    public void setMids(Long[] mids) {
        this.mids = mids;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

}
