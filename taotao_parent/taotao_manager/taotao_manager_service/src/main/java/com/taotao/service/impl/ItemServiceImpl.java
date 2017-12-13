package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public EasyUIDataGridResult getItemListByPaging(Integer page, Integer rows) {

        //设置分页信息
        PageHelper.startPage(page,rows);
        //紧跟第一个查询才会分页,调动mapper
        List<TbItem> tbItems = itemMapper.selectByExample(null);
        PageInfo<TbItem> info = new PageInfo<TbItem>(tbItems);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(info.getList());

        return result;
    }
}
