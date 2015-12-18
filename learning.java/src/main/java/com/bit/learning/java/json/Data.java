package com.bit.learning.java.json;

import java.util.List;

public class Data {
    private int age;
    private String name;
    private List<String> addresses;

    public Data() {

    }

    public Data(int age, String name, List<String> addresses) {
        this.age = age;
        this.name = name;
        this.addresses = addresses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
