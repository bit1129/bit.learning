package com.bit

import org.apache.spark.{SparkConf, SparkContext}

object WordcountTest2 {
  def main(args: Array[String]): Unit = {

    val sleepSeconds = if (args == null || args.length <= 0) 1 else args(0).toInt;

    println("The WordcountTest started to run")
    val conf = new SparkConf().setAppName("WordcountTest")
    val master = conf.getOption("spark.master") match {
      case Some(master) => master
      case _ => "local"
    }
    conf.setMaster(master)
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6))
    println(s"Waiting ${1000 * sleepSeconds} milliseconds to continue")

    rdd.map(x => if (x % 2 == 0) (2,x) else (1,x)).reduceByKey(_ + _).foreach(println)

    //When Job is Done, The shuffle data should be still there
    Thread.sleep(1000 * sleepSeconds)

    sc.stop()

  }
}
