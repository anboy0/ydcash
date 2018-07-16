package com.demo.demo.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "菜单附表")
public class BaseMenuMeta implements Serializable {

    @ApiModelProperty(name = "keepAlive", value = "是否持续连接")
    private boolean keepAlive;
    @ApiModelProperty(name = "requireAuth", value = "是否需要权限（0不需1需）")
    private boolean requireAuth;

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
