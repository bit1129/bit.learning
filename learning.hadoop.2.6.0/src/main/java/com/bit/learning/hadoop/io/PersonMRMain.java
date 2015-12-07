package com.bit.learning.hadoop.io;


import com.bit.learning.hadoop.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.io.InputStream;

class Runner extends Configured implements Tool {
    @Override
    public Configuration getConf() {
        Configuration conf = new Configuration();
        InputStream in = null;
        try {
            in = IOUtils.readFromClasspath("local.file.system.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        conf.addResource(in);
        conf.set("fs.defaultFS", "file:///") ;
        conf.set("mapreduce.framework.name", "local");
        return conf;
    }

    public Runner() {
        super();
    }

    public Runner(Configuration conf) {
        super(conf);
    }

    @Override
    public void setConf(Configuration conf) {
        super.setConf(conf);
    }

    public int run(String[] args) throws Exception {

        Job job =Job.getInstance(getConf());
        job.setMapperClass(PersonMapper.class);
        job.setReducerClass(PersonReducer.class);
        job.setInputFormatClass(PersonInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setJobName("PersonMR");
        FileInputFormat.addInputPath(job, new Path("file:///mr/input/person"));
        FileOutputFormat.setOutputPath(job, new Path("file:///mr/output" + System.currentTimeMillis()));

        //如果不设置MapOutputKeyClass和MapOutputValueClass，那么任务会执行失败，
        //也就是说，这两个设置是关键设置
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);
        boolean success =  job.waitForCompletion(true);
        System.out.println("job success: "  + success);
        return success ? 0 : -1;
    }
}

public class PersonMRMain {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Runner(), args);

    }
}
