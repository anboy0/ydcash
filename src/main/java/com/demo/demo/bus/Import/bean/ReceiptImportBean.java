package com.demo.demo.bus.Import.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.demo.demo.tools.Annotation.CheckField;

import java.io.Serializable;
import java.util.Date;

@ExcelTarget("receiptImportBean")
public class ReceiptImportBean implements Serializable {

    private transient String id;
    @Excel(name = "收款方式", orderNum = "2")
    @CheckField
    private String reciveType;
    private String reciveTypeValue;
    @CheckField
    @Excel(name = "收款公司代码", orderNum = "3")
    private String reciveCmpCode;
    private Integer reciveCmpId;
    @CheckField
    @Excel(name = "收款公司", orderNum = "4")
    private String reciveCmp;
    @CheckField
    @Excel(name = "实缴类型", orderNum = "5")
    private String cmpType;
    private String cmpTypeId;

    @Excel(name = "实缴网点/公司/个人代码", orderNum = "6")
    private String cmpCode;
    private Integer cmpId;
    @CheckField
    @Excel(name = "实缴网点/公司/个人", orderNum = "7")
    private String cmpName;
    @CheckField
    @Excel(name = "费用项目编码", orderNum = "8")
    private String chargeCode;
    @CheckField
    @Excel(name = "费用项目名称", orderNum = "9")
    private String chargeName;
    private Integer chargeId;
    @CheckField
    @Excel(name = "金额", orderNum = "10")
    private Double money;
    @CheckField
    @Excel(name = "收款日期", orderNum = "11", format = "yyyy-MM-dd")
    private Date receiveDate;
    @Excel(name = "运单号", orderNum = "12")
    private String ewb;
    @Excel(name = "备注", orderNum = "13")
    private String remark;

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getReciveCmpId() {
        return reciveCmpId;
    }

    public void setReciveCmpId(Integer reciveCmpId) {
        this.reciveCmpId = reciveCmpId;
    }

    public Integer getCmpId() {
        return cmpId;
    }

    public void setCmpId(Integer cmpId) {
        this.cmpId = cmpId;
    }

    public String getCmpTypeId() {
        return cmpTypeId;
    }

    public void setCmpTypeId(String cmpTypeId) {
        this.cmpTypeId = cmpTypeId;
    }

    public String getReciveTypeValue() {
        return reciveTypeValue;
    }

    public void setReciveTypeValue(String reciveTypeValue) {
        this.reciveTypeValue = reciveTypeValue;
    }

    public String getId() {
        return id;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReciveType() {
        return reciveType;
    }

    public void setReciveType(String reciveType) {
        this.reciveType = reciveType;
    }

    public String getReciveCmpCode() {
        return reciveCmpCode;
    }

    public void setReciveCmpCode(String reciveCmpCode) {
        this.reciveCmpCode = reciveCmpCode;
    }

    public String getReciveCmp() {
        return reciveCmp;
    }

    public void setReciveCmp(String reciveCmp) {
        this.reciveCmp = reciveCmp;
    }

    public String getCmpType() {
        return cmpType;
    }

    public void setCmpType(String cmpType) {
        this.cmpType = cmpType;
    }

    public String getCmpCode() {
        return cmpCode;
    }

    public void setCmpCode(String cmpCode) {
        this.cmpCode = cmpCode;
    }

    public String getCmpName() {
        return cmpName;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    public String getEwb() {
        return ewb;
    }

    public void setEwb(String ewb) {
        this.ewb = ewb;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
