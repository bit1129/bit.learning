package com.data.models;

public class Person {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String job;

    public Person() {

    }

    public Person(String id, String name, int age, double salary, String job) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
