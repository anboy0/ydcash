package com.demo.demo.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.demo.sys.dto.ChargeItemDto;
import com.demo.demo.sys.entity.ChargeItem;
import com.demo.demo.sys.entity.ChargeItemExport;
import com.demo.demo.sys.mapper.ChargeItemMapper;
import com.demo.demo.sys.service.IChargeItemService;

/**
 * <p>功能描述：费用项目service实现类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午12:22:30
 */
@Service
public class ChargeItemServiceImpl extends ServiceImpl<ChargeItemMapper, ChargeItem> implements IChargeItemService {

    @Override
    public Page<Map<String, Object>> selectChargeItemPage(Page<Map<String, Object>> page, ChargeItemDto dto) {
        return page.setRecords(baseMapper.selectChargeItemPage(page, dto));
    }

    @Override
    public List<Map<String, Object>> selectChargeItem(String name,Integer excludeId) {
        int start = 0;
        int end = 20;
        return baseMapper.selectChargeItem(name,excludeId,start, end);
    }

    @Override
    public ChargeItem selectByCode(String code) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.where("charge_item_code={0} and enabled={1}", code,1);
        return selectOne(wrapper);
    }

    @Override
    public Map<String, Object> selectChargeItemById(Integer id) {
        return baseMapper.selectChargeItemById(id);
    }

    @Override
    public ChargeItem selectTreeList() {
        return baseMapper.selectTreeList();
    }

	@Override
	public Integer selectRepeatCount(Integer id, String chargeItemCode) {
		return baseMapper.selectRepeatCount(id, chargeItemCode);
	}

	@Override
	public List<ChargeItemExport> selectChargeItemByMap(Map search) {
		return baseMapper.selectChargeItemByMap(search);
	}

	@Override
    public  List<ChargeItem>  selectChildrenName (int id){
        return baseMapper.selectChildrenName(id);
    }
}
