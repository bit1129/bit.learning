package com.bit.basic

/**
 * 1. 引入math包下的所有成员
 */

import scala.math._

class UnderstoreTest {


  def testUnderstoreUnsage1(): Unit = {
    /**
     * _表示的值是Int和String的默认值，默认是0和null
     */
    var a: Int = _
    var b: String = _
    println(s"$a, $b")

  }

  def testUnderstoreUsage2(): Unit {
    /**
     * [[com.bit.collections.UnderstoreStarTest]]
     */
  }

  /**
   * import all stuff under package
   */
  def testUnderstoreUsage3(): Unit {
  //TODO  import scala.math._
  }

}
