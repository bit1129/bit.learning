package com.bit.learning.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

public class LengthUDF extends UDF {

    public int evaluate(String field) {
        if (field == null) {
            return 0;
        } else {
            return field.length();
        }
    }
}
