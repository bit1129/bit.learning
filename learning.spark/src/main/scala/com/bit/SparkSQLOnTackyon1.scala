package com.bit

import org.apache.spark.sql.SQLContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkContext, SparkConf}

case class Student(name: String, age: Int)

/**
 *
 */
object SparkSQLOnTackyon1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkSQLOnTackyon1")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    /**
     * Fetch data from Tachyon
     */
    val rdd = sc.textFile("tachyon://172.18.149.133:19998/student.txt")

    import sqlContext.implicits._
    val df = rdd.map(s => s.split("")).map(x=>Student(x(0),x(1).toInt)).toDF()
    df.registerTempTable("TBL_STUDENT")

    /**
     * 1. If I  do persistence here , then I wll save another copy of data in Tachyon.
     * Also when I restart the application, how can I get back the data saved persisted in last run?
     *
     *
     * 2. If I don't do persistence,when I do many queries, every time it will fetch data from Tachyon, which is not performance effective
     */
    df.persist(StorageLevel.OFF_HEAP)
    sqlContext.sql("select name from TBL_STUDENT").collect
    sqlContext.sql("select age from TBL_STUDENT").collect
  }
}
