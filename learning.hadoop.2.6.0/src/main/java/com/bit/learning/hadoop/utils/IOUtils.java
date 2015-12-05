package com.bit.learning.hadoop.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuzhitao on 2015/12/5.
 */
public class IOUtils {
    public static InputStream readFromClasspath(String fileOnClasspath) throws IOException {
        ClassLoader loader = IOUtils.class.getClassLoader();
        InputStream in = loader.getResourceAsStream(fileOnClasspath);
        if (in == null) {
            in = loader.getResourceAsStream("/" + fileOnClasspath);
        }
        return in;
    }
}
