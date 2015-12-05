package com.bit.learning.hadoop.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (value == null || value.toString().length() <= 0) {
            return;
        }
        String str = value.toString();
        String[] tokens = str.split(" ");
        for (String token : tokens) {
            context.write(new Text(token), new IntWritable(1));
        }
    }
}
