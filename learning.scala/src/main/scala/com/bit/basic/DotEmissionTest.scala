package com.bit.basic

import org.junit.Test

class MathOp_DotEmissionTest {
  /**
   * 单参数方法
   * @param a
   * @return
   */
  def inc(a: Int) = a + 1

  /**
   * 多于一个参数的方法
   * @param a
   * @param b
   * @return
   */
  def add(a: Int, b: Int) = a + b
}

class DotEmissionTest {


  @Test
  def testDotEmission(): Unit = {
    val op = new MathOp_DotEmissionTest
    val a = op inc 1 /**只用于一次参数的情况，可以中间加空格*/
    val b = op.inc(1) /*不能写成 op.inc 1*/
    val c = op inc (1) /**可以忽略op和inc之间的.*/
    val d = op.add(1, 2) /*不能写成 op add  1 2*/
    val e = op add (1,2)/**可以忽略op和add之间的.*/
  }
}
