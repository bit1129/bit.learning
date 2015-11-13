package com.bit.basic

import org.junit.Test

class TypeErasureTest_A[T] (val a: T){

}

class TypeErasureTest {

  @Test
  def testTypeErasure(): Unit = {
    val m1 = Map(1->2, 3->4)
    val m2 = Map("One"->"Two", "Three"->"Four")

    val b = m1. isInstanceOf[Map[String,String]]

    val  a1 = new TypeErasureTest_A(1)
    val a2 = new TypeErasureTest_A("ABC")
    if (a1.isInstanceOf[TypeErasureTest_A[String]]) println("Int") else println("String")

/*
 //编译错
 a1 match {
      case x : TypeErasureTest_A[String] => println("matched")
      case _ => println("unmatched")
    }*/


  }
}
