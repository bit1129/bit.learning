package com.bit.basic

object StringTest {
  def main(ars: Array[String]): Unit = {
    val name = "Jack"
    val str = s"I am ${name + 1 + "zzz"}"
    println(str)
  }
}
