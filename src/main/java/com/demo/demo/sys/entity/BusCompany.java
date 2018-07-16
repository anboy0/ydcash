package com.demo.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author cgm123
 * @since 2018-01-23
 */
public class BusCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 公司代码
     */
    private String companyCode;
    /**
     * 公司全称
     */
    private String companyName;
    /**
     * 拼音
     */
    private String companyPy;
    /**
     * 公司营业执照号
     */
    private String companyBlNo;
    /**
     * 公司营业执照代码
     */
    private String companyBlCode;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String linkmanPhone;
    /**
     * 手机号码
     */
    private String mobilephone;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 是否可用（0否1是）
     */
    private Integer enabled;
    private Integer createBy;
    private Date createTime;
    private Integer modifyBy;
    private Date modifyTime;

    private Integer siteId;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPy() {
        return companyPy;
    }

    public void setCompanyPy(String companyPy) {
        this.companyPy = companyPy;
    }

    public String getCompanyBlNo() {
        return companyBlNo;
    }

    public void setCompanyBlNo(String companyBlNo) {
        this.companyBlNo = companyBlNo;
    }

    public String getCompanyBlCode() {
        return companyBlCode;
    }

    public void setCompanyBlCode(String companyBlCode) {
        this.companyBlCode = companyBlCode;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "BusCompany{" +
                ", id=" + id +
                ", companyCode=" + companyCode +
                ", companyName=" + companyName +
                ", companyPy=" + companyPy +
                ", companyBlNo=" + companyBlNo +
                ", companyBlCode=" + companyBlCode +
                ", linkman=" + linkman +
                ", linkmanPhone=" + linkmanPhone +
                ", mobilephone=" + mobilephone +
                ", email=" + email +
                ", address=" + address +
                ", remark=" + remark +
                ", zipCode=" + zipCode +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", modifyBy=" + modifyBy +
                ", modifyTime=" + modifyTime +
                "}";
    }
}
