package com.bit.learning.java.basics;

/**
 * Created by yuzt on 16-3-1.
 */
public class ORMapperTest {
    public static void main(String[] args) {
        String[] students = new String[]{"abc,1", "def,2","xyz,3"};

        ORMapper<Student> mapper = ORMapperFactory.getMapper("student");
        for (String student : students) {
            Student stu = mapper.map(student);
        }

    }
}
