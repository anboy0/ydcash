package com.demo.demo.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.demo.demo.sys.entity.CommonDto;

/**
 * <p>功能描述：费用项目vo</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午11:32:07
 */
@ApiModel(description = "费用项目dto")
public class ChargeItemDto extends CommonDto {
    @ApiModelProperty(name = "id", value = "费用项目ID")
    private Integer id;

    @ApiModelProperty(name = "parentId", value = "费用项目父节点ID")
    private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
