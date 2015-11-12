package com.bit.basic

import org.junit.Test


class PartialAppliedFunctionTest {


  def add(a: Int, b: Int, c: Int) = a + b + c

  /**
   * 虽然add方法的三个参数是Int类型，但是定义add的部分应用函数时，却必须指定类型，即_:Int,而不是_
   */
  val add1 = add(1, _: Int, _: Int)
  val add2 = add1(2, _: Int)
  val add3 = add2(3)

  /**
   * 部分应用函数（Partial Applied Function)是缺少部分参数的函数,
   * 偏函数(Partial Function)是只对函数定义域的一个子集进行定义的函数，偏函数在scala中使用scala.PartialFunction[-T, +S]类来表示
   */
  @Test
  def testPartialAppliedFunction(): Unit = {
    println(add1(2, 3))
    println(add2(3))
    println(add3)
  }

  /**
   * scala.MatchError: 0 (of class java.lang.Integer)
   */
  @Test
  def testPartialFunction() = {
    val a = 0
    a match {
      case x if x > 0 => println("> 0")
      case x if x < 0 => println("< 0")
    }
  }

  @Test
  def testPartialFunction2(): Unit = {

    /**
     * a是一个偏函数，只能处理输入域(Int)的一部分
     * a的类型必须显式指定，这是定义为一个function1，Int=>Unit
     */
    val a: Int => Unit = {
      case x: Int if x > 0 => println(s"a: $x> 0")
      case x: Int if x < 0 => println(s"a: $x< 0")
    }
    println("a's class name: " + a.getClass.getName)
    a(100)

    val b: PartialFunction[Int, Unit] = {
      case x: Int if x > 0 => println(s"b: $x> 0")
      case x: Int if x < 0 => println(s"b: $x< 0")
    }
    println("b's class name: " + a.getClass.getName)
    if (b.isDefinedAt(0)) println("b: 0 is in scope") else println("b: 0 is out of scope")
  }

}
