package com.bit.learning.java.basics;

class SystemCopy {
    public int x;

    @Override
    public String toString() {
        return "x: " + x;
    }
}

/**
 * System copy «ú\copy
 */
public class SystemCopyTest {
    public static void main(String[] args) {
        SystemCopy[] objects = new SystemCopy[2];
        SystemCopy obj = new SystemCopy();
        obj.x = 100;
        objects[0] = obj;
        obj = new SystemCopy();
        obj.x = 200;
        objects[1] = obj;

        SystemCopy[] targets = new SystemCopy[2];

        System.arraycopy(objects,0,targets,0,2);
        for (int i = 0; i < targets.length; i++) {
            System.out.println(targets[i]);
        }

        objects[1].x = 300;
        System.out.println(targets[1]);



    }
}
