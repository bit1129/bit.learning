package com.bit.learning.java.basics;

import java.util.*;

/***
 * 2016-02-29 11:24
 */
public class StudentArraySort {
    public static void main(String[] args) {
        Student[] students = new Student[3];

        Student s = new Student();
        s.setAge(20);
        s.setName("Jack");
        students[0] = s;

        s = new Student();
        s.setAge(10);
        s.setName("Mary");
        students[1] = s;

        s = new Student();
        s.setAge(30);
        s.setName("Don");
        students[2] = s;

        System.out.println("Arrays.sort(students);");
        Arrays.sort(students);

        for(Student student : students) {
            System.out.println(student);
        }

        System.out.println("\n\nArrays.sort(students, new Comparator<Student>()");
        Arrays.sort(students, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        for(Student student : students) {
            System.out.println(student);
        }

    }
}
