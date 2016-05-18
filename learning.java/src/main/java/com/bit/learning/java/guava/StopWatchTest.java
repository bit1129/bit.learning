package com.bit.learning.java.guava;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch watch = Stopwatch.createStarted();
        Thread.sleep(1000 * 10);
        watch.stop();
        long x = watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("elapsed: " + x);
    }
}
