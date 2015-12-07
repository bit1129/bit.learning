package com.bit.learning.hadoop.io;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PersonWritable implements WritableComparable<PersonWritable> {
    private Text name;
    private IntWritable age;

    public PersonWritable(Text name, IntWritable age) {
        this.name = name;
        this.age = age;
    }

    public PersonWritable() {
        name = new Text();
        age = new IntWritable();
    }

    public int compareTo(PersonWritable o) {
        return 0;
    }

    public void write(DataOutput out) throws IOException {
        name.write(out);
        age.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        name.readFields(in);
        age.readFields(in);
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public IntWritable getAge() {
        return age;
    }

    public void setAge(IntWritable age) {
        this.age = age;
    }
}
