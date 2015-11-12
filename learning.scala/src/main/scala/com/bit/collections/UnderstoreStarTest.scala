package com.bit.collections

import org.junit.Test

class UnderstoreStarTest {
  def print[T](elems : T*): Unit = {
    println(elems.getClass.getName) //WrappedArray
    elems.foreach(e => print(e + " "))
    println
  }

  /**
   * _*作为一个整体，告诉编译器你希望将某个参数当作参数序列(而不是单一集合对象)处理
   */
  @Test
  def testUnderstoreStar(): Unit = {
    val arr = Array(1,2,3)
    print(arr)
    print(arr: _*)
  }
}
