package com.data.models;


import java.util.ArrayList;
import java.util.List;

public class MemoryPersonFactory implements IPersonFactory {
    /**
     * Cache in memory
     */
    private static List<Person> persons = new ArrayList<Person>();

    public List<Person> get() {
        synchronized (MemoryPersonFactory.class) {
            if (persons.size() > 0) {
                return persons;
            }
            Person p = new Person("10001", "Tom", 23, 1000, "Software Engineer");
            persons.add(p);

            p = new Person("10002", "Jack", 21, 800, "Teacher");
            persons.add(p);

            p = new Person("10003", "Mary", 24, 1200, "Doctor");
            persons.add(p);

            p = new Person("10004", "Don", 22, 1000, "Software Engineer");
            persons.add(p);

            p = new Person("10005", "Don", 22, 1200, "Senior Software Engineer");
            persons.add(p);

            return persons;
        }

    }

    public void updateSalary(String id, double salary) {
        synchronized (MemoryPersonFactory.class) {
            for (Person person : persons) {
                if (person.getId().equals(id)) {
                    person.setSalary(salary);
                }
            }
        }
    }
}
