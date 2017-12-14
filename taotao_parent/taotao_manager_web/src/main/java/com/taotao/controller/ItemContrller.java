package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.ItemService;
import com.taotao.web.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemContrller {

    @Autowired
    private ItemService itemService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult showPage(Integer page, Integer rows){

        EasyUIDataGridResult itemListByPaging = itemService.getItemListByPaging(page, rows);

        return itemListByPaging;
    }

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {

        Map<String,Object> map = new HashMap<>();

        try {
            //1.获取字节数组
            byte[] bytes = uploadFile.getBytes();
            String originalFilename = uploadFile.getOriginalFilename();
            //截取文件名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastdfs_client.conf");
            String uploadFile1 = fastDFSClient.uploadFile(bytes, extName);
            //拼接图片地址
            String imageurl = IMAGE_SERVER_URL+uploadFile1;

            map.put("error",0);
            map.put("url",imageurl);

            //需要把map转化成json格式的字符串
            String json = JsonUtils.objectToJson(map);
            return json;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","上传失败！");
            String json = JsonUtils.objectToJson(map);
            return json;
        }

    }

}
