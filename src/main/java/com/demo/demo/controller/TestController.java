package com.demo.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.demo.aop.OperateLogAspect;
import com.demo.demo.exception.RestException;
import com.demo.demo.log.LogObjectHolder;
import com.demo.demo.log.bean.UserLogDict;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.RestStatus;
import com.demo.demo.sys.entity.SysUser;
import com.demo.demo.sys.service.ISysUserService;
import com.demo.demo.tools.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>功能描述：测试控制器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午4:49:32
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysUserService sysUserService;

    /**
     * 用户查询
     *
     * @param offset
     * @param limit
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Page<SysUser> list(Integer offset, Integer limit) throws Exception {
        logger.info("zhegnshan test log. info");
        logger.debug("zhegnshan test log. debug");
        logger.warn("zhegnshan test log. warn");
        logger.error("zhegnshan test log. error");
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        Page<SysUser> page = new Page<>(offset, limit);
        Page<SysUser> list = sysUserService.selectPage(page, wrapper);

        return list;
    }

    /**
     * 弹出用户修改页面
     *
     * @return
     */
    @RequestMapping("/toEdit")
    public RestStatus toEdit() {
        Integer id = 1;
        SysUser sysUser = sysUserService.selectById(id);
        LogObjectHolder.me().set(sysUser);
        return CommonStatus.OK;
    }

    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "新增用户", key = "name", dict = UserLogDict.class)
    @ResponseBody
    public RestStatus add(@RequestBody SysUser user) throws Exception {
        sysUserService.insert(user);
        return CommonStatus.ADD_OK;
    }

    @RequestMapping(value = "/edit", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "修改用户", key = "name", dict = UserLogDict.class)
    @ResponseBody
    public RestStatus edit(@RequestBody SysUser user) throws Exception {
        if (null == user.getId()) {
            throw new RestException(CommonStatus.NOT_FOUND);
        }
        sysUserService.updateById(user);
        return CommonStatus.UPDATE_OK;
    }

    @RequestMapping(value = "/del", produces = "text/html;charset=UTF-8")
    @OperateLogAspect(value = "删除用户", key = "name", dict = UserLogDict.class)
    @ResponseBody
    public RestStatus del(@RequestBody SysUser user) throws Exception {
        sysUserService = null;
        sysUserService.deleteById(user.getId());
        return CommonStatus.DELETE_OK;
    }

    /**
     * excel导出
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("exportExcel")
    public void export(HttpServletResponse response) throws Exception {
        //模拟从数据库获取需要导出的数据
        List<TestPerson> personList = new ArrayList<>();
        TestPerson person1 = new TestPerson("路飞", "1", new Date());
        TestPerson person2 = new TestPerson("娜美", "2", new Date());
        TestPerson person3 = new TestPerson("索隆", "1", new Date());
        TestPerson person4 = new TestPerson("小狸猫", "1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExcelUtil.exportExcel(personList, "草帽一伙", "草帽一伙", TestPerson.class, "海贼王.xls", response);
    }

    /**
     * excel导入
     *
     * @throws Exception
     */
    @RequestMapping("importExcel")
    public void importExcel() throws Exception {
        String filePath = "D:\\工作簿1.xlsx";
        // 1.解析excel，
        List<TestPerson> personList = ExcelUtil.importExcel(filePath, 1, 1, TestPerson.class);
        //也可以使用MultipartFile,使用 ExcelUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【" + personList.size() + "】行");

        // 2.保存数据库
    }
}
