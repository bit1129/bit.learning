package com.bit.learning.java.asm;

import java.io.*;

public class JarFTCommand {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Process p = Runtime.getRuntime().exec("jar ft " + "/home/yuzt/256hive-udf.jar");
        BufferedReader br = null;

        Thread t = new Thread() {
            @Override
            public void run() {
                BufferedReader err = null;
                try {
                    InputStream in = p.getErrorStream();
                    err = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String line = "";
                    while ((line = err.readLine()) != null) {
                        System.out.println("eeeeee: " + line);
                    }
                } catch(IOException e) {

                }finally {
                    if (err != null) {
                        try {
                            err.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t.start();
        try {
            InputStream in = p.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("zzzzzz: " + line);
            }
        } finally {
            br.close();
        }

        int code = p.waitFor();

        t.join();
    }
}
