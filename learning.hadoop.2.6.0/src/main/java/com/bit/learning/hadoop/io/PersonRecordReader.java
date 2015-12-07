package com.bit.learning.hadoop.io;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

import java.io.IOException;

/**
 * PersonRecordReader通过LineRecordReader类来实现它的功能，这是什么模式？
 * 装饰模式？-不是，装饰模式的含义是增强包装的类(这里是LineRecordReader)的功能
 */
public class PersonRecordReader extends RecordReader<LongWritable, PersonWritable> {
    private LineRecordReader lrr;

    /**
     * 一个RecordReader只持有一个PersonWritable类型的value
     */
    private PersonWritable value;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        lrr = new LineRecordReader();
        lrr.initialize(split, context);
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!lrr.nextKeyValue()) {
            return false;
        }
        Text lineText = lrr.getCurrentValue();
        if (lineText == null) {
            return false;
        }

        String str = lineText.toString();
        String[] tokens = str.split(",");
        if (tokens == null || tokens.length != 2) {
            throw new IllegalArgumentException("Invalid data line encountered: " + str);
        }

        value = new PersonWritable(new Text(tokens[0]), new IntWritable(Integer.parseInt(tokens[1])));
        return true;
    }

    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return lrr.getCurrentKey();
    }

    @Override
    public PersonWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return lrr.getProgress();
    }

    @Override
    public void close() throws IOException {
        lrr.close();
    }
}
