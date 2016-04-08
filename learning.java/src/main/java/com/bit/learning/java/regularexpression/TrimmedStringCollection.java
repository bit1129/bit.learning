package com.bit.learning.java.regularexpression;


public class TrimmedStringCollection {
    public static void main(String[] args) {
        String str = " abc   , def  ,            zzz,  msn    ";
        String[] results = getTrimmedStrings(str);
        for (String result : results) {
            if (result.length() != 3) {
                throw new RuntimeException("Error");
            }
        }
    }

    public static String[] getTrimmedStrings(String str) {
        if (null == str || str.trim().isEmpty()) {
            return new String[0];
        }

        return str.trim().split("\\s*,\\s*");
    }
}
