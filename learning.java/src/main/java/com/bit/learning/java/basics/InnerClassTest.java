package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-4-6.
 */
class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
    }
}
public class InnerClassTest {
    public void test() {
        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };
    }
}
