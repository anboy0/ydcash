package com.demo.demo.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApiModel(description = "用户表")
@ExcelTarget("BaseUser")
public class BaseUser extends CommonVo implements UserDetails, Serializable {

    private static final long serialVersionUID = -3633094448377705912L;

    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Excel(name = "姓名", orderNum = "2")
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;
    @Excel(name = "联系方式", orderNum = "8")
    @ApiModelProperty(name = "phone", value = "手机号码")
    private String phone;
    @ApiModelProperty(name = "telephone", value = "住宅电话")
    private String telephone;
    @Excel(name = "详细住址", orderNum = "6")
    @ApiModelProperty(name = "address", value = "联系地址")
    private String address;
    @ApiModelProperty(name = "enabled", value = "是否可用（0不可1可）")
    private boolean enabled;
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;
    @JSONField(serialize=false)
    @ApiModelProperty(name = "password", value = "密码")
    private String password;
    @ApiModelProperty(name = "baseRoles", value = "权限列表")
    private List<BaseRole> baseRoles;
    @ApiModelProperty(name = "baseMenus", value = "权限菜单列表")
    private List<BaseMenu> baseMenus;
    @JSONField(serialize=false)
    @ApiModelProperty(name = "userface", value = "头像")
    private String userface;
    @ApiModelProperty(name = "rids", value = "角色ids")
    private Long[] rids;
    @Excel(name = "工号", orderNum = "1")
    @ApiModelProperty(name = "usercode", value = "用户code")
    private String usercode;
    private Integer companyId;
    @Excel(name = "所属公司", orderNum = "3")
    private String companyName;
    @Excel(name = "公司代码", orderNum = "4")
    private String companyCode;
    private Integer rid;
    private String oldpassword;
    @Excel(name = "备注", orderNum = "7")
    @ApiModelProperty(name = "remark", value = "备注")
    public String remark;
    @Excel(name = "状态", orderNum = "9")
    private String statusValue;
    private boolean isdel;


    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public List<BaseRole> getBaseRoles() {
        return baseRoles;
    }

    public void setBaseRoles(List<BaseRole> baseRoles) {
        this.baseRoles = baseRoles;
    }

    public List<BaseMenu> getBaseMenus() {
        return baseMenus;
    }

    public void setBaseMenus(List<BaseMenu> baseMenus) {
        this.baseMenus = baseMenus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JSONField(serialize=false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (BaseRole baseRole : baseRoles) {
            authorities.add(new SimpleGrantedAuthority(baseRole.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long[] getRids() {
        return rids;
    }

    public void setRids(Long[] rids) {
        this.rids = rids;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public boolean isdel() {
        return isdel;
    }

    public void setIsdel(boolean isdel) {
        this.isdel = isdel;
    }
}