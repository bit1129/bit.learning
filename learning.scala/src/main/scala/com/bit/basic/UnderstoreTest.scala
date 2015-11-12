package com.bit.basic

/**
 * 1. 引入math包下的所有成员
 */

import org.junit.Test

import scala.math._

class UnderstoreTest {

  /**
   * _表示的值是Int和String的默认值，默认是0和null，如果a和b定义成局部变量，那么不能用这种写法
   *
   */

  var a: Int = _
  var b: String = _

  def testUnderstoreUnsage1() {


    a = 10
    b = "10"
    println(s"$a, $b")

  }

  def testUnderstoreUsage2() {
    /**
     * [[com.bit.collections.UnderstoreStarTest]]
     */
  }

  /**
   * import all stuff under package
   * import scala.math._
   */
  def testUnderstoreUsage3() {
    sqrt(100)
  }


  /**
   * case pattern case _ :
   */
  def testUnderstoreUsage4() {
    val a: PartialFunction[Int, Unit] = {
      case x if x > 100 => println("x>100")
      case x if x < -100 => println("x<-100")
      case _ => println("[-100,100]")
    }
    a(20)
  }

  /**
   * Tuple
   */
  @Test
  def testUnderstoreUsage5() {
    val b = (100, 200)
    println(b._1)
  }


}
