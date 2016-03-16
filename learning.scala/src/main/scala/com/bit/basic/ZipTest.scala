package com.bit.basic

object ZipTest {
  def main(args: Array[String]): Unit = {
    val a = Seq("a")
    val b = Seq("b");

    //c是(a,b)
    val c = a.zip(b)
    c.foreach(println)

    val d = Seq()
    val e = a.zip(d);

    //e是空集合
    e.foreach(println)
  }

}
