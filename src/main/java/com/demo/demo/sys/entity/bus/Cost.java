package com.demo.demo.sys.entity.bus;

import com.demo.demo.sys.entity.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "费用项目表")
public class Cost extends CommonVo {

    @ApiModelProperty(name = "chargeItemCode", value = "费用项目编码")
    private String chargeItemCode;
    @ApiModelProperty(name = "chargeItemName", value = "费用项目名称")
    private String chargeItemName;
    @ApiModelProperty(name = "parent", value = "父节点")
    private Integer parent;
    @ApiModelProperty(name = "enabled", value = "是否可用（0否1是）")
    private boolean enabled;

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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
