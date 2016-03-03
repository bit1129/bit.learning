package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-3-3.
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        Integer i = 1;
        System.out.println(testTryCatchFinally(i));

        i = null;
        System.out.println(testTryCatchFinally(i));
    }

    public static Integer testTryCatchFinally(Integer i) {

        try {
            int a = i + 10;
            return a;
        } catch (Exception e) {
            return -1;
        } finally {
            System.out.println("Hello");
        }
    }
}
