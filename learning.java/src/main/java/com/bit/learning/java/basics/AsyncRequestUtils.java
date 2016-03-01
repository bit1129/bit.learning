package com.bit.learning.java.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yuzt on 16-3-1.
 */
public class AsyncRequestUtils {
    /**
     * 异步读取URI对应的资源，将读取结果通过callback返回
     * 为什么url和callback是final的？
     *
     * @param url
     * @param callback
     */
    public static void async(final URL url, final AsyncCallback callback) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                BufferedReader br = null;
                try {
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    br = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (sb.length() > 0) {
                            sb.append(System.lineSeparator());
                        }
                        sb.append(line);
                    }

                    //异步处理成功，进行回调
                    callback.onSuccess(sb.toString());

                } catch (Exception e) {
                    //异步处理失败，进行回调
                    callback.onFailure(e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t.start();
    }
}
