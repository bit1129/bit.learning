package com.bit.learning.java.basics;

public class Singleton2 {

    //INSTNCE持有Single2的唯一实例
    private static  Singleton2 INSTANCE = null;

    //静态方法，获取唯一的单例对象
    public static Singleton2 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }

    private Singleton2() {

    }
}
