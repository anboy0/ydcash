package com.demo.demo.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.demo.demo.sys.dto.ChargeItemDto;
import com.demo.demo.sys.entity.ChargeItem;
import com.demo.demo.sys.entity.ChargeItemExport;

/**
 * <p>功能描述：费用项目接口</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:20:26
 */
public interface ChargeItemMapper extends BaseMapper<ChargeItem> {
    /**
     * 分页查询费用项目信息
     *
     * @param page
     * @param dto
     * @return
     */
    List<Map<String, Object>> selectChargeItemPage(Pagination page, ChargeItemDto dto);

    /**
     * 模糊查询费用项目编码、费用项目名称
     *
     * @param name
     * @param start
     * @param end
     * @return
     */
    List<Map<String, Object>> selectChargeItem(@Param("name") String name,@Param("excludeId") Integer excludeId,@Param("start") Integer start, @Param("end") Integer end);

    /**
     * 根据ID查询费用项目
     *
     * @param id
     * @return
     */
    Map<String, Object> selectChargeItemById(@Param("id") Integer id);

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
    Integer selectRepeatCount(@Param("id") Integer id, @Param("chargeItemCode") String chargeItemCode);
    
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
