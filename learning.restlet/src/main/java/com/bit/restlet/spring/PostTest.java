package com.bit.restlet.spring;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yuzt on 16-3-8.
 */
public class PostTest {
    public static void main(String[] args) {
//        String url = "http://localhost:8091/helloworld/data?x=1&y=2";

        String url = "http://localhost:8080/myapp/helloworld/data?x=1&y=2";
        HttpURLConnection conn = null;
        OutputStream os = null;
        BufferedReader br = null;

        try {
            File hiveJarFile = new File("/home/yuzt/big.java");

            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(30000);
            conn.connect();

            OutputStream out = conn.getOutputStream();
            IOUtils.copy(new FileInputStream(hiveJarFile), out);

            out.flush();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
