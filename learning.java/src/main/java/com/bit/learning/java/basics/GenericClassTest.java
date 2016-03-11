package com.bit.learning.java.basics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GenericClassTest {

    public static String stringifyCollection(Collection<String> objects) {
        Iterator<String> iterator = objects.iterator();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()) {
            sb.append(iterator.next()).append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("xyz");
        String str = stringifyCollection(list);
        System.out.println(str);

    }

}

