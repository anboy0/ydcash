package com.demo.demo.exception;

import com.demo.demo.response.RestStatus;

/**
 * <p>功能描述：restful接口相关异常</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午5:17:49
 */
public class RestException extends Exception {
    private static final long serialVersionUID = 1L;
    private RestStatus status;

    public RestException() {
        super();
    }

    public RestException(RestStatus status) {
        this.status = status;
    }


    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestStatus getStatus() {
        return status;
    }

    public void setStatus(RestStatus status) {
        this.status = status;
    }
}
