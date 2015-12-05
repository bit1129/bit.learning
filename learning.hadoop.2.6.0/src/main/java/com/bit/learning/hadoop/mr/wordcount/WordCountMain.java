package com.bit.learning.hadoop.mr.wordcount;


import com.bit.learning.hadoop.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
//        job.setJarByClass(WordCountMain.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setJobName("WordCountMain");
        FileInputFormat.addInputPath(job, new Path("file:///mr/input"));
        FileOutputFormat.setOutputPath(job, new Path("file:///mr/output" + System.currentTimeMillis()));
        job.setNumReduceTasks(2);
        boolean success =  job.waitForCompletion(true);
        System.out.println("job success: "  + success);
        return success ? 0 : -1;
    }
}
public class WordCountMain {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Runner(), args);

    }
}
