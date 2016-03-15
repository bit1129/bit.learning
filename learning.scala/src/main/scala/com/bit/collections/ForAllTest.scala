package com.bit.collections

/**
  * Created by yuzt on 16-3-14.
  */
object ForAllTest {
  def main(args: Array[String]): Unit = {
    val arr = Seq(1, 3, 5);
    arr.sliding(2).map {
      case Seq(a) => ("abc")
      case Seq(a, b) => println("def")
    }.foreach(println)
  }

}
