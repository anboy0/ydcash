package com.demo.demo.sys.entity.bus;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.demo.demo.sys.entity.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "网点表")
@ExcelTarget("Site")
public class Site extends CommonVo implements Serializable {

    private static final long serialVersionUID = 2831690967893484392L;

    @Excel(name = "网点编号", orderNum = "1")
    @ApiModelProperty(name = "siteCode", value = "网点编号")
    private String siteCode;
    @Excel(name = "网点名称", orderNum = "2")
    @ApiModelProperty(name = "siteName", value = "网点名称")
    private String siteName;
    @ApiModelProperty(name = "siteType", value = "网点类型")
    private Integer siteType;
    @ApiModelProperty(name = "siteServiceType", value = "网点服务类型")
    private Integer siteServiceType;
    @ApiModelProperty(name = "status", value = "网点状态")
    private String status;
    @ApiModelProperty(name = "parent", value = "父节点")
    private Integer parent;
    @ApiModelProperty(name = "created", value = "创建人（名称）")
    private String created;
    @ApiModelProperty(name = "modified", value = "修改人（名称）")
    private String modified;
    @Excel(name = "所属网点", orderNum = "6")
    @ApiModelProperty(name = "siteName2", value = "所属网点")
    private String siteName2;
    @Excel(name = "网点类型", orderNum = "3")
    private String siteTypeValue;
    @Excel(name = "网点服务类型", orderNum = "4")
    private String siteServiceTypeValue;
    @Excel(name = "网点状态", orderNum = "5")
    private String siteStatusValue;
    private Integer siteId;

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public Integer getSiteServiceType() {
        return siteServiceType;
    }

    public void setSiteServiceType(Integer siteServiceType) {
        this.siteServiceType = siteServiceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSiteName2() {
        return siteName2;
    }

    public void setSiteName2(String siteName2) {
        this.siteName2 = siteName2;
    }

    public String getSiteTypeValue() {
        return siteTypeValue;
    }

    public void setSiteTypeValue(String siteTypeValue) {
        this.siteTypeValue = siteTypeValue;
    }

    public String getSiteServiceTypeValue() {
        return siteServiceTypeValue;
    }

    public void setSiteServiceTypeValue(String siteServiceTypeValue) {
        this.siteServiceTypeValue = siteServiceTypeValue;
    }

    public String getSiteStatusValue() {
        return siteStatusValue;
    }

    public void setSiteStatusValue(String siteStatusValue) {
        this.siteStatusValue = siteStatusValue;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}
