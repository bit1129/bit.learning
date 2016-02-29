package com.bit.learning.java.basics;

import java.util.*;

/***
 * 2016-02-29 11:24
 */
public class StudentArraySort {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();

        Student s = new Student();
        s.setAge(20);
        s.setName("Jack");
        students.add(s);

        s = new Student();
        s.setAge(10);
        s.setName("Mary");
        students.add(s);

        s = new Student();
        s.setAge(30);
        s.setName("Don");
        students.add(s);

        Student[] studentArray = students.toArray(new Student[0]);

        Arrays.sort(studentArray);

        System.out.println("Sort List:");

        for (Student student : studentArray) {
            System.out.println(student);
        }

        Collections.sort(students);

        for (Student student : students) {
            System.out.println(student);
        }

    }
}
