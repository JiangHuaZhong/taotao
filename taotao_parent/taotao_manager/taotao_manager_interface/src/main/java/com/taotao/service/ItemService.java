package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemService {

    EasyUIDataGridResult getItemListByPaging(Integer page, Integer rows);
}
