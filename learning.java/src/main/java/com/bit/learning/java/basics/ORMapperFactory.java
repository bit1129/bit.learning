package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-3-1.
 */
public class ORMapperFactory {
    public static ORMapper getMapper(String mapperName) {
        if ("student".equals(mapperName)) {
            return new StudentMapper();
        }
        return null;
    }
}
