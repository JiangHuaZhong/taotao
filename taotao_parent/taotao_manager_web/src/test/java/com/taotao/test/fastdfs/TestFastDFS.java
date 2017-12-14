package com.taotao.test.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

public class TestFastDFS {

    @Test
    public void tsetFastdfs() throws Exception {

        //创建配置文件fastdfs.conf，并设置内容为IMAGE_SERVER_URL=http://192.168.25.133/
        //1.加载配置文件
        ClientGlobal.init("E:\\taotao\\taotao_parent\\taotao_manager_web\\src\\main\\resources\\fastdfs_client.conf");
        //2.创建TrackerClient 通过上面的获取链接对象
        TrackerClient trackerClient = new TrackerClient();
        //3.获取trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //4.获取storgeclient 需要两个参数  一个是trackerserver 一个是storageserver
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        String[] strings = storageClient.upload_appender_file("G:\\BigPhoto\\No.020 Foxlag\\33.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }

    }

}
