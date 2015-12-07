package com.bit.learning.java.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuzhitao on 2015/12/6.
 */
public class SandboxChecker {

    private static Map<String, Set<String>> IN_SANDBOX = new HashMap<String, Set<String>>();
    private static Map<String, Set<String>> NOT_IN_SANDBOX = new HashMap<String, Set<String>>();

    static {
        Set<String> methods = new HashSet<String>();
        methods.add("read");
        methods.add("write");
        IN_SANDBOX.put(UnsafeOperation.class.getName(), methods);
    }


    /**
     * 检查当前线程中是否包含不安全操作，如果包含，则抛出Permission异常
     *
     * @param t
     * @return
     */
    public static boolean isInSandbox(Thread t) {
        if (t == null) {
            return false;
        }
        StackTraceElement[] elements = t.getStackTrace();
        for (StackTraceElement element : elements) {
            String className = element.getClassName();
            String methodName = element.getMethodName();
            if (isInSandbox(className, methodName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInSandbox(String className, String methodName) {
        Set<String> methodNames = IN_SANDBOX.get(className);
        if (methodNames == null || methodNames.size() <= 0) {
            return false;
        }
        return methodNames.contains(methodName);
    }

}
