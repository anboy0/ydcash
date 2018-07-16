package com.demo.demo.log.enums;

/**
 * <p>功能描述：日志的业务类型</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午2:25:03
 */
public enum LogType {
    LOGIN("登录日志"),
    EXCEPTION("异常日志"),
    OPERATE("业务日志");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
