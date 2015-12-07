package com.bit.learning.java.security;

public class SecurityTest {
    public static void main(String[] args) {
        try {
            LearningSecurityManager bsm = new LearningSecurityManager();
            System.setSecurityManager(bsm);

            //每个操作都会进行permission check？
            UnsafeOperation op = new UnsafeOperation();
            op.read();
        } finally {
            System.setSecurityManager(null);
        }
    }
}
