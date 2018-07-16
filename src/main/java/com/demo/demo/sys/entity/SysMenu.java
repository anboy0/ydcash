package com.demo.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author cgm123
 * @since 2018-01-08
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单相应地址
     */
    private String action;
    /**
     * 菜单状态
     */
    private Integer status;
    /**
     * 菜单功能标志(1为菜单,0为功能)
     */
    private Integer flag;
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                ", id=" + id +
                ", name=" + name +
                ", sort=" + sort +
                ", pid=" + pid +
                ", icon=" + icon +
                ", action=" + action +
                ", status=" + status +
                ", flag=" + flag +
                ", url=" + url +
                "}";
    }
}
