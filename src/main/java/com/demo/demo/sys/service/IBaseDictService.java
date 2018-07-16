package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.entity.BaseDict;

/**
 * <p>
 * 系统字典表 服务类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-17
 */
public interface IBaseDictService extends IService<BaseDict> {
    BaseDict hasTypeName(String type, String name);
}
