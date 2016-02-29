package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-2-29.
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("run in Runnable#run");
            }
        };

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("run in Thread#run");
            }
        };
        t1.start();
        t1.join();

        Thread t2 = new Thread(r);
        t2.start();
        t2.join();


        Thread t3 = new Thread(r) {
            @Override
            public void run() {
                System.out.println("run in Thread#run");
            }
        };
        t3.start();


    }
}
