package com.bit.learning.java.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhitao on 2016/3/9.
 */
public class ObjectTest {
    public static void test(Object...args) {
        System.out.println(args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println(i+ ": " + args[i]);
        }
        System.out.println("===============================");
    }

    public static void main(String[] args) {
        Object[] xy = new Object[]{"x","y"};
        List<String> ab = new ArrayList<String>();
        int[] digits = new int[]{100,200};
        ab.add("a");
        ab.add("b");
        test(xy);
        test(ab);
        test(digits);
    }
}
