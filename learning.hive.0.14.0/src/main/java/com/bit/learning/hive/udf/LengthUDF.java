package com.bit.learning.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.IOException;
import java.net.InetAddress;

public class LengthUDF extends UDF {

    public int evaluate(String field) {
//        try {
//            Class.forName("java.lang.String");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            File tmp = new File("/home/yuzt/" + System.currentTimeMillis());
//            tmp.createNewFile();
//            tmp.delete();
//        } catch(Exception e) {
//
//        }

//        new Thread();

        if (field == null) {
            return 0;
        } else {
            return field.length();
        }
    }

    public InetAddress hello() throws IOException {
        return InetAddress.getLocalHost();
    }
}
