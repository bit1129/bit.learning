package com.bit.learning.java.basics;

import java.io.*;

/**
 * Created by yuzt on 16-3-7.
 */
public class IOUtils {
    /***
     * 将输入流复制到输出流，可以用于文件复制
     *
     * @param in
     * @param out
     */
    public static void copy(InputStream in, OutputStream out) throws IOException {
        //每次复制1024字节
        byte[] bytes = new byte[1024];
        int length = 0;
        try {
            while ((length = in.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        String srcFilePath = "/home/yuzt/hive.log";
        String targetFilePath = srcFilePath + ".copy";
        File target = new File(targetFilePath);
        if (!target.exists()) {
            target.createNewFile();
        }

        IOUtils.copy(new FileInputStream(srcFilePath), new FileOutputStream(target));

        BufferedReader br = new BufferedReader(new FileReader(target));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }


    }
}
