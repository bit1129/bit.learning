package com.bit.learning.hadoop.io;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;
import java.util.List;

/**
 * InputFormat的自定义实现，需要实现两个方法
 * 1）createRecordReader，返回的是 RecordReader<LongWritable, PersonWritable>
 * 2）getSplits返回的是InputSplit的集合
 *
 * RecordReader和InputSplit需要考虑实现
 */
public class PersonInputFormat extends FileInputFormat<LongWritable, PersonWritable> {
    public PersonInputFormat() {
        super();
    }

    @Override
    public RecordReader<LongWritable, PersonWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        return new PersonRecordReader();
    }

}
