package com.demo.demo.log.bean;

import java.util.HashMap;

/**
 * <p>功能描述：日志字典抽象类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午10:35:18
 */
public abstract class AbstractLogDict {

    /**
     * 字段和字段名称的映射关系
     */
    protected HashMap<String, String> fieldNameMap = new HashMap<>();

    /**
     * 字段和获取字段值方法的映射
     */
    protected HashMap<String, String> methodNameMap = new HashMap<>();

    public AbstractLogDict() {
        initFieldNameMap();
        initMethodNameMap();
    }

    /**
     * 初始化-字段和字段名称的映射关系
     */
    public abstract void initFieldNameMap();

    /**
     * 初始化-字段和获取字段值方法的映射
     */
    protected abstract void initMethodNameMap();

    public String getFieldName(String key) {
        return this.fieldNameMap.get(key);
    }

    public void putFieldName(String key, String value) {
        this.fieldNameMap.put(key, value);
    }

    public String getMethodName(String key) {
        return this.methodNameMap.get(key);
    }

    public void putMethodName(String key, String methodName) {
        this.methodNameMap.put(key, methodName);
    }
}
