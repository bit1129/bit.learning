package com.bit.learning.java.basics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yuzhitao on 2016/3/13.
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();

        strings.add("abc");
        strings.add("def");
        strings.add("xyz");

        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            String str= iterator.next();
            if ("abc".equals(str)) {
//                strings.remove(str);
                iterator.remove();
                continue;
            }
            System.out.println(str);
        }

    }
}
