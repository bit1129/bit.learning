package com.bit.restlet.spring;


import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by yuzt on 16-3-8.
 */
public class PostTest2 {
    public static void main(String[] args) {
//        String url = "http://localhost:8091/helloworld/data?x=1&y=2";

        String url = "http://localhost:8080/myapp/helloworld/data";
        HttpURLConnection conn = null;
        OutputStream os = null;
        BufferedReader br = null;

        try {
            File hiveJarFile = new File("/home/yuzt/big.java");
            InputStream in = new FileInputStream(hiveJarFile);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            String str = HttpUtils.post(url, out.toByteArray());
            System.out.println(str);
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } finally {
            release(conn, os, br);
        }


    }

    private static void release(HttpURLConnection conn, OutputStream os, BufferedReader br) {
        if (null != conn) {
            conn.disconnect();
        }

        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
