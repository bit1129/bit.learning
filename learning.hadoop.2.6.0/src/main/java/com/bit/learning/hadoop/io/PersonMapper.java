package com.bit.learning.hadoop.io;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PersonMapper extends Mapper<LongWritable, PersonWritable, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, PersonWritable value, Context context) throws IOException, InterruptedException {
        context.write(value.getName(), value.getAge());
    }
}
