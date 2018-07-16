package com.demo.demo.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author cgm123
 * @since 2018-01-08
 */
@ExcelTarget("sysUser")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @Excel(name = "姓名", orderNum = "1")
    private String name;
    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "3")
    private String email;
    /**
     * 电话
     */
    @Excel(name = "电话", orderNum = "2")
    private String tel;
    /**
     * 密码
     */
    @Excel(name = "密码", orderNum = "4")
    private String pwd;
    /**
     * 创建时间
     */
    @Excel(name = "密码", orderNum = "5", format = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 用户名
     */
    private String username;
    /**
     * 拼音首字母
     */
    private String py;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                ", id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", tel=" + tel +
                ", pwd=" + pwd +
                ", createTime=" + createTime +
                ", status=" + status +
                ", loginTime=" + loginTime +
                ", username=" + username +
                ", py=" + py +
                "}";
    }
}
