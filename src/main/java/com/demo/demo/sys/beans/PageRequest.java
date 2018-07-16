package com.demo.demo.sys.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;

@ApiModel(description = "通用分页查询请求实体")
public class PageRequest implements Serializable {
    @ApiModelProperty("开始时间")
    String startTime;
    @ApiModelProperty("结束时间")
    String endTime;
    @ApiModelProperty("类型")
    String type;
    @ApiModelProperty("ID")
    Integer id;
    @ApiModelProperty("查询条件")
    String search;
    @ApiModelProperty("状态")
    Integer status;
    @ApiModelProperty("第几页")
    Integer page;
    @ApiModelProperty("单页条数")
    Integer pageSize;
    @ApiModelProperty("复杂查询条件")
    HashMap<String, Object> mapSearch;

    public HashMap<String, Object> getMapSearch() {
        return mapSearch;
    }

    public void setMapSearch(HashMap<String, Object> mapSearch) {
        this.mapSearch = mapSearch;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
