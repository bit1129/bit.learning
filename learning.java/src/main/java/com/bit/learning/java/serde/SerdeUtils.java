package com.bit.learning.java.serde;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerdeUtils {

    /**
     * @param objects
     * @return
     * @throws IOException
     */
    public static String list2String(List<? extends Serializable> objects) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(objects);
        oos.close();
        byte[] bytes = baos.toByteArray();
        Base64 base64 = new Base64();
        return new String(base64.encode(bytes));
    }

    /**
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<? extends Serializable> string2List(String str) throws IOException, ClassNotFoundException {
        byte[] b = str.getBytes();
        Base64 base64 = new Base64();
        b = base64.decode(b);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream ois = new ObjectInputStream(bais);
        List<? extends Serializable> objects = (List<? extends Serializable>) ois.readObject();
        ois.close();
        return objects;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 3; i++) {
            Person p = new Person();
            p.setName("name-" + i);
            p.setAge("age-" + i);
            persons.add(p);
        }
        String str = list2String(persons);

        persons = (List<Person>) string2List(str);
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }


    }


}
