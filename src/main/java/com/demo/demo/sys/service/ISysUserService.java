package com.demo.demo.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.entity.SysUser;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-08
 */
public interface ISysUserService extends IService<SysUser> {

    Page<SysUser> selectUserPage(Page<SysUser> page, Map search);
}
