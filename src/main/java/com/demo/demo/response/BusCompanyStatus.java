package com.demo.demo.response;

public enum BusCompanyStatus implements RestStatus {
    BUS_ADD_OK(200, "新增公司成功!"),
    BUS_ADD_ERROR(201, "新增公司成功!");
    int code;
    String msg;

    BusCompanyStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int status() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
