package com.demo.demo.sys.beans;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class LoginBean implements Serializable {
    @ApiModelProperty(notes = "登录用户名")
    String userName;
    @ApiModelProperty(notes = "校验密码,值为MD5(MD(username+MD5(pwd))+verifycode)")
    String pwdMd5;
    @ApiModelProperty(notes = "验证码")
    String verifyCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwdMd5() {
        return pwdMd5;
    }

    public void setPwdMd5(String pwdMd5) {
        this.pwdMd5 = pwdMd5;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
