package com.demo.demo.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.entity.SysUser;
import com.demo.demo.sys.mapper.SysUserMapper;
import com.demo.demo.sys.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cgm123
 * @since 2018-01-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Page<SysUser> selectUserPage(Page<SysUser> page, Map search) {
        return page.setRecords(baseMapper.selectPageByMap(page, search));
    }
}
