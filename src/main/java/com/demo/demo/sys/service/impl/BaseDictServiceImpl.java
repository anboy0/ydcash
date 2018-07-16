package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.entity.BaseDict;
import com.demo.demo.sys.mapper.BaseDictMapper;
import com.demo.demo.sys.service.IBaseDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典表 服务实现类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-17
 */
@Service
public class BaseDictServiceImpl extends ServiceImpl<BaseDictMapper, BaseDict> implements IBaseDictService {

    @Override
    @Cacheable(value = "BaseDictServiceImpl", key = "'dict_id:'+#id")
    public BaseDict selectById(Serializable id) {
        return super.selectById(id);
    }

    @Override
    @Cacheable(value = "BaseDictServiceImpl", key = "#columnMap.get('type_name')+'#'+#columnMap.get('status')")
    public List<BaseDict> selectByMap(Map<String, Object> columnMap) {
        return super.selectByMap(columnMap);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "BaseDictServiceImpl", key = "#entity.typeName+'#'+#entity.status")
            },
            put = {
                    @CachePut(value = "BaseDictServiceImpl", key = "'dict_id:'+#id"),
                    @CachePut(value = "BaseDictServiceImpl", key = "#entity.typeName+':v'+#entity.name")
            }
    )
    @Override
    public boolean updateById(BaseDict entity) {
        return super.updateById(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "BaseDictServiceImpl", key = "#entity.typeName+'#'+#entity.status"),
                    @CacheEvict(value = "BaseDictServiceImpl", key = "#entity.typeName+':v'+#entity.name")
            }
    )

    @Override
    public boolean insert(BaseDict entity) {
        return super.insert(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "BaseDictServiceImpl", key = "#entity.typeName+'#'+#entity.status"),
                    @CacheEvict(value = "BaseDictServiceImpl", key = "#entity.typeName+':v'+#entity.name")
            }
    )
    @Override
    public boolean insertOrUpdate(BaseDict entity) {
        return super.insertOrUpdate(entity);
    }

    @CacheEvict(value = "BaseDictServiceImpl")
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    @Cacheable(value = "BaseDictServiceImpl", key = "#type+':v'+#name")
    public BaseDict hasTypeName(String type, String name) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.where("type_name={0} and status={1}", type,1).and("name={0}", name);
        return selectOne(wrapper);
    }
}
