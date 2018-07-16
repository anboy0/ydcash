package com.demo.demo.response;

/**
 * <p>功能描述：返回结果对象</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @param <T>
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午4:41:06
 */
public class ResponseData<T> {

    /**
     * 返回状态码
     */
    private int status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;


    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseData() {

    }

    public ResponseData(RestStatus status) {
        this.status = status.status();
        this.message = status.message();
    }

    public ResponseData(T data, RestStatus status) {
        this.data = data;
        this.status = status.status();
        this.message = status.message();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}