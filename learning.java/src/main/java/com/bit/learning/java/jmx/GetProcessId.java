package com.bit.learning.java.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class GetProcessId {
    public static final String getPid() {
        String pid = null;
        final RuntimeMXBean runtimeMX = ManagementFactory.getRuntimeMXBean();
        final String name = runtimeMX.getName();
        System.out.println(name);
        final int index = name.indexOf("@");
        if (index != -1) {
            pid = name.substring(0, index);
        }
        return pid;
    }

    public static void main(String[] args) throws InterruptedException {
        String pid = getPid();
        System.out.println("pid is " + pid);
        Thread.sleep(15 * 1000);
    }
}
