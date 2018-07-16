package com.demo.demo.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.demo.demo.sys.dto.ChargeItemDto;
import com.demo.demo.sys.entity.ChargeItem;
import com.demo.demo.sys.entity.ChargeItemExport;

/**
 * <p>功能描述：费用项目service接口</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:22:30
 */
public interface IChargeItemService extends IService<ChargeItem> {
    /**
     * 分页查询费用项目信息
     *
     * @param page
     * @param dto
     * @return
     */
    Page<Map<String, Object>> selectChargeItemPage(Page<Map<String, Object>> page, ChargeItemDto dto);

    /**
     * 模糊查询费用项目编码、费用项目名称
     *
     * @param name
     * @return
     */
    List<Map<String, Object>> selectChargeItem(String name,Integer excludeId);

    ChargeItem selectByCode(String code);

    /**
     * 根据ID查询费用项目
     *
     * @param id
     * @return
     */
    Map<String, Object> selectChargeItemById(Integer id);

    /**
     * 
     * 查询整颗费用项目树
     * @return
     */
    ChargeItem selectTreeList();
    
    /**
     * 
     * 查询费用项目编码是否重复
     * @param id
     * @param chargeItemCode
     * @return
     */
    Integer selectRepeatCount(Integer id, String chargeItemCode);
    
    /**
     * 
     * 根据条件模糊查询费用项目
     * @param search
     * @return
     */
    List<ChargeItemExport> selectChargeItemByMap(Map search);
    /**
     *
     * 判断费用项目是否有孩子
     * @param
     * @return
     */
    List<ChargeItem>  selectChildrenName (int id);
}
