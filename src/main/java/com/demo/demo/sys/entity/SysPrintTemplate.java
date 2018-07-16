package com.demo.demo.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author cgm123
 * @since 2018-01-12
 */
public class SysPrintTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板内容
     */
    private String text;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 创建人ID
     */
    private Integer createId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysPrintTemplate{" +
                ", id=" + id +
                ", name=" + name +
                ", text=" + text +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createId=" + createId +
                ", remark=" + remark +
                "}";
    }
}
