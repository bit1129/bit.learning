package com.bit.leanring.hive.hiveapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;

import java.util.List;

/**
 * This is used to play with Hive API.
 * 1. To make it work, the follow configuration files should be places on the classpath, they are usuallly placed under the resources directory
 * 1)hive-site.xml
 * 2)core-site.xml
 * 3)hdfs-site.xml
 * 4)mapred-site.xml
 * 5)yarn-site.xml
 *
 *  2. Hadoop/Hive related jars should be on the classpath when runnning HiveUsage
 *
 */
public class HiveUsage {
    public static void main(String[] args) throws HiveException {
        System.out.println("=============================Start to run HiveUsage==========================");
        Configuration conf = new Configuration();
        HiveConf hc = new HiveConf(conf, HiveUsage.class);
        Hive hive = Hive.get(hc);
        String tableName;
        String dbName;
        if (args == null || args.length <= 0) {
            dbName = "db1";
            tableName = "table1";
        } else if (args.length == 1){
            dbName = "db1";
            tableName = args[0].trim();
        } else if (args.length == 2) {
            dbName = args[0].trim();
            tableName = args[1].trim();
        } else {
            throw new IllegalArgumentException("The arguments should be [dbName] [tableName]");
        }
        Table t = hive.getTable(dbName,tableName);

        System.out.println(tableName + "'s input class is : " + t.getInputFormatClass().getName());
        System.out.println(tableName + "'s output class is : " + t.getOutputFormatClass().getName());

        List<FieldSchema> schemas = t.getCols();
        System.out.println(tableName + " column information: ");
        for (FieldSchema schema : schemas) {
            String name = schema.getName();
            String type = schema.getType();
            System.out.println("\tname: " + name + ", type=" + type);
        }
        if (! t.isPartitioned()) {
            System.out.println(tableName + " is not a partitioned table");
        } else {
            System.out.println(tableName + " is a partitioned table,its partitions information");
            List<FieldSchema> partitionKeys = t.getPartitionKeys();
            for (FieldSchema schema: partitionKeys) {
                String name = schema.getName();
                String type = schema.getType();
                System.out.println("\tname: " + name + ", type=" + type);
            }
        }

        //针对分区表继续进行API用法的测试
        if (t.isPartitioned()) {

        }


    }
}
