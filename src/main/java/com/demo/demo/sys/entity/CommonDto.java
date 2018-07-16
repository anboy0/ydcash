package com.demo.demo.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "基础Dto")
public class CommonDto {
    @ApiModelProperty(name = "page", value = "当前页数")
    private int page;

    @ApiModelProperty(name = "pageSize", value = "每一页显示的条数")
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
