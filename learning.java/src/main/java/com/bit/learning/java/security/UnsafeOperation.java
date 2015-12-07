package com.bit.learning.java.security;

import java.io.File;

/**
 * Created by yuzhitao on 2015/12/6.
 */
public class UnsafeOperation {
    public String read() {
        return "Something is read";
    }
    public void write(File target) {
        System.out.println("Write something to the the target : " + target.getAbsolutePath());
    }

}
