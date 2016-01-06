package com.bit

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}


object Parquet2Text {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Parquet2Text")
    val master = conf.getOption("spark.master") match {
      case Some(master) => master
      case _ => "local[1]"
    }
    conf.setMaster(master)
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc);

    val table = "store_sales"; //customer, customer_address, store

    val parquet = sqlContext.read.parquet("hdfs://ns1/tmp/spark_perf/scaleFactor=30/useDecimal=true/" + table)
    parquet.printSchema();
    parquet.registerTempTable("tbl_" + table);
    val df = sqlContext.sql("select * from tbl_" + table)
    df.rdd.map(row => {
      val sb = new StringBuilder();
      for (i <- 0 until row.length) {
        if (i > 0) {
          sb.append("\t")
        }
        sb.append(row.get(i))
      }
      sb.toString()
    }).saveAsTextFile(s"hdfs://ns1/tmp/spark_perf_parquet2text/$table" + "1/")


  }
}
