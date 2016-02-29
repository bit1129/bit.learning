package com.bit.learning.java.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/***
 * 2016-02-29 11:24
 */
public class PersonArraySort {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        Person p = new Person();
        p.setAge(20);
        p.setName("Jack");
        persons.add(p);

        p = new Person();
        p.setAge(10);
        p.setName("Mary");
        persons.add(p);

        p = new Person();
        p.setAge(30);
        p.setName("Don");
        persons.add(p);

        Person[] personArray = persons.toArray(new Person[0]);
        Arrays.sort(personArray, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Person x : personArray) {
            System.out.println(x);
        }

    }
}
