package com.demo.demo.bus.Import.base;

import com.demo.demo.response.RestStatus;

public enum ImportRespones implements RestStatus {
    CLASS_ERROR(201, "找不到该导入类"),
    EMPTY_ERROR(202, "导入数据为空");
    int code;
    String msg;

    @Override
    public int status() {
        return this.code;
    }

    @Override
    public String message() {
        return this.msg;
    }

    ImportRespones(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
