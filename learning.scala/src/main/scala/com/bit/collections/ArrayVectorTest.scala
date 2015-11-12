package com.bit.collections

import org.junit.Test


class ArrayVectorTest {
  @Test
  def testArrayVector(): Unit = {
    val v = Vector(1, 2, 3)
    val arr = v.toArray
    arr.foreach(x=>print(x + " "))
    println

    /**
     * arr作为Vector的唯一一个元素，也就是v1只有一个元素，这个元素是个数组
     */
    val v1 = Vector(arr)

    /**
     *将arr元素展开后存放到Vector中，因此_*是集合展开的意思
     */
    val v2 = Vector(arr:_*)
    println(v2.size)
    v2.foreach(x=> print(x+" "))
  }
}
