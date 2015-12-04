package com.bit.learning.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(
        name = "strlen",
        value = " _FUNC_(str) - return the length of string, if str is null,then its length will be 0 ",
        extended = "select _FUNC_(name) from TBL_STUDENT limit 3")
public class StringLengthUDF extends UDF {

    public int evaluate(String field) {
        if (field == null) {
            return 0;
        } else {
            return field.length();
        }
    }
}
