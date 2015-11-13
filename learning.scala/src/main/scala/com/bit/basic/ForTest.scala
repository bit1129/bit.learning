package com.bit.basic

import org.junit.Test


class ForTest {
  @Test
  def testForVal(): Unit = {
    val arr = Array(1, 2, 3)

    /**
     for (val x <- arr)有错
     */
    for ( x <- arr) {
      print(x + " ")
    }
  }
}
