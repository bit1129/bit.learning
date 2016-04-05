package com.bit.learning.java.asm;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class JarAnalysis {
    public static void main(String[] args) throws Exception {

    }

    public static byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(bytes))) {
            output.write(bytes, 0, n);
        }
        return output.toByteArray();
    }

    public static Map<String, byte[]> className2Bytes(String jarPath) throws Exception {
        Map<String, byte[]> data = new HashMap<String, byte[]>();
        if (jarPath == null) {
            jarPath = "/home/yuzt/development/myprojects/bit.learning/learning.hive.0.14.0/target/learning.hive.0.14.0-1.0-SNAPSHOT.jar";
        }
        URL jarURL = new File(jarPath).toURI().toURL();
        JarInputStream in = null;
        List<String> classes = new ArrayList<String>();
        URLClassLoader loader = (URLClassLoader) JarAnalysis.class.getClassLoader();

        List<URL> curPath = Arrays.asList(loader.getURLs());

        List<URL> newPaths = new ArrayList<URL>();
        for (URL url : curPath) {
            newPaths.add(url);
        }

        newPaths.add(jarURL);

        ClassLoader classLoader = new URLClassLoader(newPaths.toArray(new URL[0]), loader);
        Thread.currentThread().setContextClassLoader(classLoader);

        try {
            InputStream is = new FileInputStream(jarPath);
            JarEntry entry = null;
            in = new JarInputStream(is);

            while ((entry = in.getNextJarEntry()) != null) {
                String name = entry.getName();
                if (name.endsWith("class")) {
                    classes.add(name);
                }
                System.out.println(name);
            }

            for (String className : classes) {
                InputStream iss = Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
                if (className.endsWith("LengthUDF.class")) {
                    byte[] bytes = inputStream2Bytes(iss);
                    data.put(className, bytes);
                    System.out.println("className: " + className + "," + bytes.length);
                }
                iss.close();
            }

            return data;


        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
