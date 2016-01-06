package com.bit.restlet.standalone;

/**
 * Created by yuzt on 16-1-6.
 */
public class Book {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
