package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-3-3.
 *
 *
 */
class A {
    public int age;
}
class B {
    A a;
}
public class Assignment {
    public static void main(String[] args) {
        B b = new B();
        A obj = b.a;
        System.out.println(obj);
        b.a = new A();
        System.out.println(obj);

    }
}
