package com.demo.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>功能描述：费用项目vo</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午11:32:07
 */
@ApiModel(description = "费用项目vo")
@TableName("bus_charge_item")
public class ChargeItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "chargeItemCode", value = "费用项目编码")
    private String chargeItemCode;

    @ApiModelProperty(name = "chargeItemName", value = "费用项目名称")
    private String chargeItemName;

    @ApiModelProperty(name = "parentId", value = "父节点ID")
    private int parentId;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "enabled", value = "是否可用（0否，1是）")
    private boolean enabled;

    @ApiModelProperty(name = "createBy", value = "创建人")
    private int createBy;

    @ApiModelProperty(name = "createTime", value = "创建日期")
    private Date createTime;

    @ApiModelProperty(name = "modifyBy", value = "修改人")
    private int modifyBy;

    @ApiModelProperty(name = "modifyTime", value = "修改日期")
    private Date modifyTime;

    @ApiModelProperty(name = "childrenList", value = "子节点列表")
    @TableField(exist = false)
    private List<ChargeItem> childrenList;

    public List<ChargeItem> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChargeItem> childrenList) {
        this.childrenList = childrenList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChargeItemCode() {
        return chargeItemCode;
    }

    public void setChargeItemCode(String chargeItemCode) {
        this.chargeItemCode = chargeItemCode;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
