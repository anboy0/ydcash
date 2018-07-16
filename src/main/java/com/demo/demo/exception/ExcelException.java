package com.demo.demo.exception;

import com.demo.demo.response.RestStatus;

/**
 * <p>功能描述：excel导入、导出相关异常</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午5:17:49
 */
public class ExcelException extends RestException {
    private static final long serialVersionUID = 1L;
    private RestStatus status;

    public ExcelException() {
        super();
    }

    public ExcelException(RestStatus status) {
        this.status = status;
    }


    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestStatus getStatus() {
        return status;
    }

    public void setStatus(RestStatus status) {
        this.status = status;
    }
}
