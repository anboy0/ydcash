package com.demo.demo.sys.entity.bus;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@ExcelTarget("ReceiptExport")
public class ReceiptExport implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    @Excel(name = "流水号", orderNum = "1")
    String sn;
    @Excel(name = "开票日期", orderNum = "11", format = "yyyy-MM-dd")
    Date createTime;
    @Excel(name = "打印次数", orderNum = "13")
    Integer printCount;
    @Excel(name = "最后打印日期", orderNum = "12", format = "yyyy-MM-dd")
    Date printTime;
    @Excel(name = "冲销日期", orderNum = "16")
    Date writeOffTime;
    @Excel(name = "收款日期", orderNum = "10", format = "yyyy-MM-dd")
    Date receiveTime;
    @Excel(name = "收款操作人", orderNum = "14")
    String createBy;
    @Excel(name = "汇总金额", orderNum = "7")
    Double money;
    @Excel(name = "开票人", orderNum = "14")
    String writeOffBy;
    @Excel(name = "收费方式", orderNum = "9")
    String payType;
    @Excel(name = "实缴类型", orderNum = "4")
    String cmpType;
    @Excel(name = "票据状态", orderNum = "8")
    String status;
    @Excel(name = "冲销金额", orderNum = "15")
    Double writeOffMoney;
    @Excel(name = "缴费公司/个人/网点代码", orderNum = "5")
    String cmpCode;
    @Excel(name = "缴费公司/个人/网点", orderNum = "6")
    String cmpName;
    @Excel(name = "收款公司", orderNum = "3")
    String rCmpName;
    @Excel(name = "收款公司代码", orderNum = "2")
    String rCmpCode;

    public String getrCmpName() {
        return rCmpName;
    }

    public void setrCmpName(String rCmpName) {
        this.rCmpName = rCmpName;
    }

    public String getrCmpCode() {
        return rCmpCode;
    }

    public void setrCmpCode(String rCmpCode) {
        this.rCmpCode = rCmpCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Date getWriteOffTime() {
        return writeOffTime;
    }

    public void setWriteOffTime(Date writeOffTime) {
        this.writeOffTime = writeOffTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getWriteOffBy() {
        return writeOffBy;
    }

    public void setWriteOffBy(String writeOffBy) {
        this.writeOffBy = writeOffBy;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCmpType() {
        return cmpType;
    }

    public void setCmpType(String cmpType) {
        this.cmpType = cmpType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getWriteOffMoney() {
        return writeOffMoney;
    }

    public void setWriteOffMoney(Double writeOffMoney) {
        this.writeOffMoney = writeOffMoney;
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

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
