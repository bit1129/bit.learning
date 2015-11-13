package com.bit.basic

import org.junit.Test


class ForTest {
  @Test
  def testForVal(): Unit = {
    val arr = Array(1, 2, 3)

    /**
    for (val x <- arr)有错
      */
    for (x <- arr) {
      print(x + " ")
    }

    /**
     * val y 能够使用，不过deprecated
     */
    for (x <- arr; val y = x) {
      print(y + " ")
    }
  }
}
