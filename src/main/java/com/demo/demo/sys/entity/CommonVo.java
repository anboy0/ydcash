package com.demo.demo.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "基础vo")
public class CommonVo {

    @ApiModelProperty(name = "id", value = "ID")
    public Integer id;
    @ApiModelProperty(name = "remark", value = "备注")
    public String remark;
    @ApiModelProperty(name = "createBy", value = "创建人")
    public Long createBy;
    @ApiModelProperty(name = "modifyBy", value = "创建时间")
    public Long modifyBy;
    @ApiModelProperty(name = "createTime", value = "修改人")
    public Date createTime;
    @ApiModelProperty(name = "modifyTime", value = "修改时间")
    public Date modifyTime;
    @JSONField(serialize=false)
    @ApiModelProperty(name = "page", value = "当前页数")
    public int page;
    @JSONField(serialize=false)
    @ApiModelProperty(name = "pageSize", value = "每一页显示的条数")
    public int pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
