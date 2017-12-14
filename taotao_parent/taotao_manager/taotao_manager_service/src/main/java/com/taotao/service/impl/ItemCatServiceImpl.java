package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatListByParentId(Long parentId) {

        TbItemCatExample itemCatExample = new TbItemCatExample();
        itemCatExample.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = tbItemCatMapper.selectByExample(itemCatExample);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();

        for (TbItemCat itemCat : itemCats) {
                EasyUITreeNode node = new EasyUITreeNode();
                node.setId(itemCat.getId());
                node.setText(itemCat.getName());
                node.setState(itemCat.getIsParent()?"closed":"open");
                easyUITreeNodes.add(node);
        }

        return easyUITreeNodes;
    }
}
