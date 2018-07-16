package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.entity.BusCompany;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-23
 */
public interface IBusCompanyService extends IService<BusCompany> {
    Page<BusCompany> selectByMap(Page<BusCompany> page, Map search);

    BusCompany getCmpByCode(String code);
}
