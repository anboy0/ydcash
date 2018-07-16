package com.demo.demo.sys.dto;

import com.demo.demo.sys.entity.CommonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>功能描述：收款vo</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午11:32:07
 */
@ApiModel(description = "收款dto")
public class ReceiptDto extends CommonDto {
    @ApiModelProperty(name = "id", value = "ID")
    private Integer id;

    @ApiModelProperty(name = "ids", value = "ids")
    private String ids;

	@ApiModelProperty(name = "startTime", value = "查询开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "查询结束时间")
    private Date endTime;

    @ApiModelProperty(name = "payType", value = "收费方式,1現金 2转账 3支票")
    private String payType;

    @ApiModelProperty(name = "serialNo", value = "流水号")
    private String serialNo;

    @ApiModelProperty(name = "createByName", value = "收款操作人")
    private String createByName;

    @ApiModelProperty(name = "status", value = "票据状态，1生效，0已冲销")
    private String status;

    @ApiModelProperty(name = "chargeItemId", value = "费用项目ID")
    private int chargeItemId;

    @ApiModelProperty(name = "payName", value = "缴费公司/个人/网点")
    private String payName;

    @ApiModelProperty(name = "ewbNo", value = "运单号")
    private String ewbNo;

    @ApiModelProperty(name = "writeOffByName", value = "冲销操作人")
    private String writeOffByName;

    //add by xinglei
    @ApiModelProperty(name = "companyid", value = "员工所属公司ID")
    private Integer companyid;

    //add by xinglei
    @ApiModelProperty(name = "createBy", value = "创建人")
    private Integer createBy;

    //add by hsk
    @ApiModelProperty(name = "receiveCompanyId", value = "收款公司编号")
    private Integer receiveCompanyId;

    public Integer getReceiveCompanyId() {
        return receiveCompanyId;
    }

    public void setReceiveCompanyId(Integer receiveCompanyId) {
        this.receiveCompanyId = receiveCompanyId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getChargeItemId() {
		return chargeItemId;
	}

	public void setChargeItemId(int chargeItemId) {
		this.chargeItemId = chargeItemId;
	}

	public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getEwbNo() {
        return ewbNo;
    }
    public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
    public void setEwbNo(String ewbNo) {
        this.ewbNo = ewbNo;
    }

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getWriteOffByName() {
		return writeOffByName;
	}

	public void setWriteOffByName(String writeOffByName) {
		this.writeOffByName = writeOffByName;
	}
}
