package com.demo.demo.bus.Export.base;

import com.demo.demo.response.RestStatus;

public enum ExportRespones implements RestStatus {
    CLASS_ERROR(201, "找不到该导出类"),
    EMPTY_ERROR(202, "导出数据为空"),
    FILE_ERROR(203, "上传文件异常"),
    ANALYSE_ERROR(204, "上传文件解析异常,请检查文件格式!"),
    OUT_ERROR(205, "服务器输出异常!"),
    FILE_NOT_EXIST(206, "文件不存在!");
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

    ExportRespones(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
