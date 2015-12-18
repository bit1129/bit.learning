package com.bit.learning.java.json;

import java.util.ArrayList;
import java.util.List;

public class GsonUtils {
    public static void main(String[] args) {
        List<ResponseObject<Data>> objects = new ArrayList<ResponseObject<Data>>();
        ResponseObject<Data> object = new ResponseObject<Data>();
        object.setCode(1);
        object.setMsg("Hello,world");
        List<String> addresses = new ArrayList<String>();
        addresses.add("A1");
        addresses.add("A2");
        Data d1 = new Data(1, "A", addresses);
        object.setData(d1);
        objects.add(object);



    }
}
