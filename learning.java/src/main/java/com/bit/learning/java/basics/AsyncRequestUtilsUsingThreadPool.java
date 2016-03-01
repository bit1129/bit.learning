package com.bit.learning.java.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

/**
 * Created by yuzt on 16-3-1.
 */
public class AsyncRequestUtilsUsingThreadPool {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);

    /***
     * Shut down thread pool when JVM is shut down
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                EXECUTOR.shutdown();
            }
        }));
    }

    /**
     * 异步读取URI对应的资源，将读取结果通过callback返回
     * 为什么url和callback是final的？
     *
     * @param url
     * @param callback
     */
    public static void async(final URL url, final AsyncCallback callback) {
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
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
                    return sb.toString();
                } catch (Exception e) {
                    throw e;
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
        };
        Future<String> future = EXECUTOR.submit(callable);
        try {
            String response = future.get();
            callback.onSuccess(response);
        } catch (Exception e) {
            callback.onFailure(e.getMessage());
            e.printStackTrace();
        }

    }
}
