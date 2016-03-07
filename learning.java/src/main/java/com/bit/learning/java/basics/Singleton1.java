package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-3-7.
 */
public class Singleton1 {

    //INSTANCE是单例对象，它是static public final的,
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1() {

    }
}
