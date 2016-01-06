package com.bit.hbase.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class PutTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        /**
         * t1 should exist beforehand
         */
        HTable table = new HTable(conf, "t1");
        Put put = new Put(Bytes.toBytes("row-1"));


        /**
         * cf1 should exist beforehand.
         * c1,c2 don't necessary to exist beforehand,that is, they are created automatically,this is what schemaless means
         */
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("c1"), Bytes.toBytes("value1"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("c2"), Bytes.toBytes("value2"));
        table.put(put);
    }
}
