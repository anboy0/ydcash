package com.demo.demo.sys.entity.bus;

import com.demo.demo.sys.entity.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "公司表")
public class Company extends CommonVo {

    @ApiModelProperty(name = "companyCode", value = "编码")
    private String companyCode;
    @ApiModelProperty(name = "comPanyName", value = "名称")
    private String comPanyName;
    @ApiModelProperty(name = "CompanyPy", value = "名称拼音")
    private String CompanyPy;
    @ApiModelProperty(name = "companyBlNo", value = "公司营业执照号")
    private String companyBlNo;
    @ApiModelProperty(name = "companyBlCode", value = "公司营业执照代码")
    private String companyBlCode;
    @ApiModelProperty(name = "linkman", value = "联系人")
    private String linkman;
    @ApiModelProperty(name = "linkmanPhone", value = "联系电话")
    private String linkmanPhone;
    @ApiModelProperty(name = "mobilephone", value = "手机号码")
    private String mobilephone;
    @ApiModelProperty(name = "email", value = "邮箱地址")
    private String email;
    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;
    @ApiModelProperty(name = "zipCode", value = "邮编")
    private String zipCode;
    @ApiModelProperty(name = "enabled", value = "是否可用（0不可1可）")
    private boolean enabled;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getComPanyName() {
        return comPanyName;
    }

    public void setComPanyName(String comPanyName) {
        this.comPanyName = comPanyName;
    }

    public String getCompanyPy() {
        return CompanyPy;
    }

    public void setCompanyPy(String companyPy) {
        CompanyPy = companyPy;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
