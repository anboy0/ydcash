package com.demo.demo.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.demo.demo.sys.entity.BusCompany;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cgm123
 * @since 2018-01-23
 */
public interface BusCompanyMapper extends BaseMapper<BusCompany> {
    List<BusCompany> selectByMap(Pagination page, Map search);
}
