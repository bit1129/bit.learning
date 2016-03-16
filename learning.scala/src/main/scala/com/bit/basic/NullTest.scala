package com.bit.basic

/**
  * Created by yuzt on 16-3-15.
  */
object NullTest {
  def main(args: Array[String]): Unit = {
    val str = "abc";
    val str2: String = null;
    println(str != str2)
  }
}
