package com.demo.demo.log.bean;

/**
 * <p>功能描述：用户日志字典</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午10:44:38
 */
public class UserLogDict extends AbstractLogDict {
    @Override
    public void initFieldNameMap() {
        putFieldName("name", "账号");
        putFieldName("username", "姓名");
        putFieldName("email", "电子邮件");
        putFieldName("tel", "电话");
        putFieldName("status", "状态");
    }

    @Override
    protected void initMethodNameMap() {
        putMethodName("status", "getUserStatusById");
    }
}
