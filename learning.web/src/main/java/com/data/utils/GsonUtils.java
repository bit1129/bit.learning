package com.data.utils;


import com.google.gson.Gson;

public class GsonUtils {
    private Gson GSON = new Gson();

    public <T> String java2String(T obj) {
        return GSON.toJson(obj);
    }

    public <T> T json2Java(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
