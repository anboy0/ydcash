package com.demo.demo.bus.Import.base;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseImport<T> {
    protected List<T> data = new ArrayList<>();
    protected List<T> errData = new ArrayList<>();
    protected Class cls;

    public abstract void doAnalyze(InputStream inputStream) throws Exception;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getErrData() {
        return errData;
    }

    public void setErrData(List<T> errData) {
        this.errData = errData;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public int getSize() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public int getErrSize() {
        if (errData != null) {
            return errData.size();
        } else {
            return 0;
        }
    }

}
