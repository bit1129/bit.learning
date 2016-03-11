package com.bit.restlet.spring;

import java.io.File;

/**
 * Created by yuzt on 16-3-10.
 */
public class PostTestMinicPresto {
    public static void main(String[] args) throws Exception {
        String fileName = "presto-hive-test-jar";
        String url = "http://192.168.177.80:8888/action/udf/api/addResource?userName=datajingdo_m&&fileName=" + fileName;
        File file = new File("/home/yuzt/hive.0.14.0-1.0-SNAPSHOT.jar");
        String str = HttpUtils.postMinicPresto(file, url);
        System.out.println(str);


    }
}
