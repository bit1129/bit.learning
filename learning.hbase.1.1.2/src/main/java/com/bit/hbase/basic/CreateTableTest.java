package com.bit.hbase.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


public class CreateTableTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        String tableName = "hbase_test_" + System.currentTimeMillis();
        HTableDescriptor tableDescriptor = new HTableDescriptor(Bytes.toBytes(tableName));
        HColumnDescriptor columnDescriptor = new HColumnDescriptor(Bytes.toBytes("colfam1"));
        tableDescriptor.addFamily(columnDescriptor);
        admin.createTable(tableDescriptor);
        boolean isAvailable = admin.isTableAvailable(tableName);
        System.out.println("Table is available: " + isAvailable);

        if (isAvailable) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }

        isAvailable = admin.isTableAvailable(tableName);
        System.out.println("Table is available: " + isAvailable);
    }
}
