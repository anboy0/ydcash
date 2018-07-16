package com.demo.demo.sys.enums;

import com.demo.demo.sys.beans.ErrorBean;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements Serializable {
    LOGIN_OK(0, "登录成功!"),
    LOGIN_ERROR(-1, "登录失败,请检查用户名密码!"),
    ACCESS_OK(0, "请求成功!"),
    VERIFY_ERROR(-2, "验证码错误!"),
    SESSION_ERROR(-3, "非法请求或Session超时或用户未登录!"),
    ACCESS_ERROR_PRINT(-4, "打印模板请求异常!"),
    ERR_EXCEPTION(-99, "后台异常!");

    private int code;

    private String desc;

    ErrorCode(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public ErrorBean getResult() {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setCode(this.getCode());
        errorBean.setMsg(this.getDesc());
        return errorBean;
    }

    public ErrorBean getResult(Object data) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setCode(this.getCode());
        errorBean.setMsg(this.getDesc());
        errorBean.setData(data);
        return errorBean;
    }
}
