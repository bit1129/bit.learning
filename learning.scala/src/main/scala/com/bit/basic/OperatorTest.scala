package com.bit.basic

import org.junit.{Assert, Test}


class OperatorTest {

  /**
   * 测试按位与
   */
  @Test
  def test1() {
    val a  = 13
    val b = 11
    val c = a & b
    Assert.assertEquals(9, c)
  }
}
