package com.data.models;

import java.util.List;

/**
 * Created by yuzt on 16-4-8.
 */
public interface IPersonFactory {
    List<Person> get();
    public void updateSalary(String id, double salary);
}
