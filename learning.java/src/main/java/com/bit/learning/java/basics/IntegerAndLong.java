package com.bit.learning.java.basics;

public class IntegerAndLong {

    /**
     * int类型可以自动转换为long？
     * @param x
     * @param y
     * @return
     */
    private  static long calc(long x, long y) {
        return x + y;
    }

    private static int calc(int x, int y) {
        return x*y;
    }


    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        System.out.println(calc(10,20));
    }
}
