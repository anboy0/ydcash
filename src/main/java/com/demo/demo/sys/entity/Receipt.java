package com.demo.demo.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>功能描述：收款vo</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午11:32:07
 */
@ApiModel(description = "收款vo")
public class Receipt implements Serializable {
    private static long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "ids", value = "ids")
    private String ids;
    
    @ApiModelProperty(name = "serialNo", value = "流水号")
    private String serialNo;

    @ApiModelProperty(name = "receiveCompanyId", value = "收款公司ID")
    private Integer receiveCompanyId;

    @ApiModelProperty(name = "payCompanyType", value = "实缴公司类型，1网点 2公司 3个人")
    private String payCompanyType;

    @ApiModelProperty(name = "payCompanyId", value = "实缴公司ID")
    private Integer payCompanyId;

    @ApiModelProperty(name = "payName", value = "实缴个人名称")
    private String payName;

    @ApiModelProperty(name = "payType", value = "收费方式,1現金 2转账 3支票")
    private String payType;

    @ApiModelProperty(name = "totalMoney", value = "汇总金额")
    private double totalMoney;

    @ApiModelProperty(name = "status", value = "票据状态，1生效，0已冲销,2保存到数据中")
    private String status;

    @ApiModelProperty(name = "receiveTime", value = "收款日期")
    private Date receiveTime;

    @ApiModelProperty(name = "createBy", value = "收款操作人")
    private Integer createBy;

    @ApiModelProperty(name = "createTime", value = "开票日期")
    private Date createTime;

    @ApiModelProperty(name = "printTime", value = "打印日期")
    private Date printTime;

    @ApiModelProperty(name = "printCount", value = "打印次数")
    private Integer printCount;

    @ApiModelProperty(name = "writeOffTime", value = "冲销日期")
    private Date writeOffTime;

    @ApiModelProperty(name = "writOffBy", value = "冲销操作人")
    private Integer writOffBy;

    @ApiModelProperty(name = "modifyBy", value = "修改人")
    private Integer modifyBy;

    @ApiModelProperty(name = "modifyTime", value = "修改日期")
    private Date modifyTime;

    @ApiModelProperty(name = "receiptDetail", value = "收款明细")
    private List<ReceiptDetail> receiptDetail;
	//add by xinglei
	@ApiModelProperty(name = "companyid", value = "员工所属公司ID")
	private Integer companyid;

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getReceiveCompanyId() {
		return receiveCompanyId;
	}

	public void setReceiveCompanyId(Integer receiveCompanyId) {
		this.receiveCompanyId = receiveCompanyId;
	}

	public String getPayCompanyType() {
		return payCompanyType;
	}

	public void setPayCompanyType(String payCompanyType) {
		this.payCompanyType = payCompanyType;
	}

	public Integer getPayCompanyId() {
		return payCompanyId;
	}

	public void setPayCompanyId(Integer payCompanyId) {
		this.payCompanyId = payCompanyId;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Date getWriteOffTime() {
		return writeOffTime;
	}

	public void setWriteOffTime(Date writeOffTime) {
		this.writeOffTime = writeOffTime;
	}

	public Integer getWritOffBy() {
		return writOffBy;
	}

	public void setWritOffBy(Integer writOffBy) {
		this.writOffBy = writOffBy;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<ReceiptDetail> getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(List<ReceiptDetail> receiptDetail) {
		this.receiptDetail = receiptDetail;
	}
}
