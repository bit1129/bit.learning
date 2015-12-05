package com.bit.leanring.hive.hiveapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is used to play with Hive API.
 * 1. To make it work, the follow configuration files should be places on the classpath, they are usuallly placed under the resources directory
 * 1)hive-site.xml
 * 2)core-site.xml
 * 3)hdfs-site.xml
 * 4)mapred-site.xml
 * 5)yarn-site.xml
 *
 *  2. Hadoop/Hive related jars should be on the classpath when running HiveUsage
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

        //如果参数只是表名，那么使用的是当前数据库，如果参数是数据库名.表名的形式，那么按照.进行切割
//        Table t2 = hive.getTable(dbName + "." + tableName);
//        System.out.println("hive.getTable(dbName + \".\" + tableName also works: " + t2.getTableName());

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
            List<FieldSchema> partitionKeys = t.getPartitionKeys();
            String partName = partitionKeys.get(0).getName();
            String partValue = "20151205";
            Map<String, String> partitionSpec = new HashMap<String, String>();
            partitionSpec.put(partName,partValue);
            List<Partition> partitions = hive.getPartitions(t, partitionSpec);
            if (partitions == null || partitions.size() <= 0) {
                throw new RuntimeException("Unable to get the partition, partitions is null? " + (partitions == null));
            } else {
                Partition p = partitions.get(0);
                System.out.println("Partition " + p.getName() + "'s data location is " + p.getDataLocation());


                String delim = p.getParameters().get("field.delim");
                System.out.println("delim returned from partition is: " + delim);
                String delim2 =  t.getSerdeParam("field.delim");
                if (delim == null) {
                    System.out.println("delim returned from partition is null, table delim should also be null? " + (delim2 == null));
                }else {
                    System.out.println("delim returned from table is: " + delim2 +", they are equals: " + delim.equals(delim2));
                }

                System.out.println("Get schema information via Partition.getCols,it should return the same result as Table.getCols");
                List<FieldSchema> schemas1 = p.getCols();
                for (FieldSchema schema : schemas1) {
                    String name = schema.getName();
                    String type = schema.getType();
                    System.out.println("\tname: " + name + ", type=" + type);
                }
            }
        }
    }
}
