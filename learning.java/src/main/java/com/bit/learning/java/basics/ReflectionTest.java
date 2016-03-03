package com.bit.learning.java.basics;

import java.lang.reflect.Constructor;


class Arg {
    private final String arg;

    public Arg(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return "The arg is: " + arg;
    }
}

public class ReflectionTest {
    public ReflectionTest(String a, Arg arg) {
        System.out.println(a + arg);
    }

    public static void main(String[] args) throws Exception {
        ClassLoader loader = ReflectionTest.class.getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }

        Class clazz = loader.loadClass("com.bit.learning.java.basics.ReflectionTest");
        Constructor c = clazz.getConstructor(String.class, Arg.class);
        ReflectionTest instance = (ReflectionTest) c.newInstance("Hello, World", new Arg("123"));
    }
}
