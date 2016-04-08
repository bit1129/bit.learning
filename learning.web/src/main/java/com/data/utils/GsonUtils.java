package com.data.utils;


import com.google.gson.Gson;

public class GsonUtils {
    private static Gson GSON = new Gson();

    public static <T> String java2String(T obj) {
        return GSON.toJson(obj);
    }

    public static <T> T json2Java(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
