package com.bit.learning.java.designpatterns.strategy;

/**
 * Created by yuzt on 16-3-15.
 */
public class MemoryStorageService implements IStorageService {
    private String storage;

    public void save(String data) {
        storage = data;
    }

    public String read() {
        return storage;
    }
}
