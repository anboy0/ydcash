package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.entity.BusCompany;
import com.demo.demo.sys.mapper.BusCompanyMapper;
import com.demo.demo.sys.service.IBusCompanyService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-23
 */
@Service
public class BusCompanyServiceImpl extends ServiceImpl<BusCompanyMapper, BusCompany> implements IBusCompanyService {


    @Override
    public Page<BusCompany> selectByMap(Page<BusCompany> page, Map search) {
        return page.setRecords(baseMapper.selectByMap(page, search));
    }

    @Cacheable(value = "BusCompanyServiceImpl", key = "#code")
    @Override
    public BusCompany getCmpByCode(String code) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.where("company_code={0}", code).and("enabled=1");
        return selectOne(wrapper);
    }

    @CachePut(value = "BusCompanyServiceImpl", key = "#entity.companyCode")
    @Override
    public boolean insertOrUpdate(BusCompany entity) {
        return super.insertOrUpdate(entity);
    }

    @CacheEvict(value = "BusCompanyServiceImpl")
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @CachePut(value = "BusCompanyServiceImpl", key = "#entity.companyCode")
    @Override
    public boolean updateById(BusCompany entity) {
        return super.updateById(entity);
    }
}
