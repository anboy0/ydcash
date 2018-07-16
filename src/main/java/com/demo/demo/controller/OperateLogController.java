package com.demo.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.sys.entity.OperateLog;
import com.demo.demo.sys.service.IOperateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>功能描述：操作日志控制器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午4:49:32
 */
@RestController
@RequestMapping("/operateLog")
public class OperateLogController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IOperateLogService operateLogService;

    /**
     * 操作日志查询
     *
     * @param offset
     * @param limit
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Page<OperateLog> list(Integer page, Integer pageSize, String startTime, String endTime, String logname) throws Exception {
        Wrapper<OperateLog> wrapper = new EntityWrapper<OperateLog>();
//		wrapper.eq("logname", logname);
        wrapper.between("create_time", startTime, endTime);
        Page<OperateLog> pager = new Page<>(page, pageSize);
        Page<OperateLog> list = operateLogService.selectPage(pager, wrapper);
        return list;
    }
}
