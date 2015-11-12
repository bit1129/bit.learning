package com.bit

import org.apache.spark.{SparkContext, SparkConf}

object WordcountTest {
  def main(args: Array[String]): Unit = {
    println("The simpleTest started to run")
    val conf = new SparkConf().setAppName("SimpleTest")
   val (master,sleepSeconds) = conf.getOption("spark.master") match {
      case Some(master) => {
        (master, 300)
      }
      case _ => {
        ("local[1]",1)
      }
    }
    conf.setMaster(master)
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6))
    println(s"Waiting ${1000*sleepSeconds} milliseconds to continue")
    Thread.sleep(1000*sleepSeconds)
    val sum = rdd.reduce(_ + _)
    println(s"The sum is : $sum")
    sc.stop()

  }
}
