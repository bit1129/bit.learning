package com.bit.basic

object BitTest {
  def main(args : Array[String]): Unit = {
    val a = 100L
    val b = 200L
    val c = (a | b) & b
    println(s"c is $c")
  }
}
