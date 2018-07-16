package com.demo.demo.sys.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p>功能描述：费用项目导出对象</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午11:32:07
 */
public class ChargeItemExport {
	@Excel(name = "费用项目编码", orderNum = "0")
    private String chargeItemCode;

	@Excel(name = "费用项目名称", orderNum = "1")
    private String chargeItemName;
	
	@Excel(name = "所属费用项目", orderNum = "2")
    private String parentName;

	@Excel(name = "创建人", orderNum = "3")
    private String createByName;

	@Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "4")
    private Date createTime;

	@Excel(name = "修改人", orderNum = "5")
    private String modifyByName;

	@Excel(name = "修改时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "6")
    private Date modifyTime;
	
	@Excel(name = "备注", orderNum = "7")
    private String remark;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getModifyByName() {
		return modifyByName;
	}

	public void setModifyByName(String modifyByName) {
		this.modifyByName = modifyByName;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
