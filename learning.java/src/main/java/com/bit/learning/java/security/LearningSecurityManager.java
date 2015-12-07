package com.bit.learning.java.security;

import java.security.Permission;

/**
 *  安全管理器，只允许执行沙箱中定义的操作，
 *  问题：用什么描述沙箱中一个操作？用类名+方法名？
 */
public class LearningSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        Thread t = Thread.currentThread();

        if (SandboxChecker.isInSandbox(t)) {
            super.checkPermission(perm);
        }
    }

    @Override
    public void checkAccess(Thread t) {
        if (t.getState() == Thread.State.NEW && SandboxChecker.isInSandbox(Thread.currentThread()) ) {
            throw new  java.security.AccessControlException("Creating new thread is not allowd in sandbox");
        }
    }
}
