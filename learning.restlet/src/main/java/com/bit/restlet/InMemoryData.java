package com.bit.restlet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuzt on 16-1-6.
 */
public class InMemoryData {
    private Set<Task> tasks = new HashSet<Task>();
    private Set<Book> books = new HashSet<Book>();

    private InMemoryData() {

    }

    public static InMemoryData data = new InMemoryData();

    public void add(Book book) {
        books.add(book);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(Book book) {
        books.remove(book);
    }

    public boolean delete(Task task) {
        boolean b = tasks.remove(task);
        return b;
    }
}
