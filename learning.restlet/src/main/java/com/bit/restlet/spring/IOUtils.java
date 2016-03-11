package com.bit.restlet.spring;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/***
 *
 */
public class IOUtils {
    /***
     * 将输入流数据写入到输出流中，操作完成后，输入流和输出流都会关闭
     *
     * @param in
     * @param out
     */
    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] bytes = new byte[1024];
        int length;
        try {
            while ((length = in.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
            out.flush();
        } finally {
            close(in, out);
        }
    }

    /**
     * @param resources
     */
    private static void close(Closeable... resources) {
        if (resources == null) {
            return;
        }
        for (Closeable resource : resources) {
            try {
                resource.close();
            } catch (IOException e) {
                //Ignore
            }
        }

    }
}
