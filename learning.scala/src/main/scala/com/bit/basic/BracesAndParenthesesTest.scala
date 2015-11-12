package com.bit.basic

import org.junit.Test

//http://stackoverflow.com/questions/4386127/what-is-the-formal-difference-in-scala-between-braces-and-parentheses-and-when
//http://hongjiang.info/scala-pitfalls-2/
class BracesAndParenthesesTest {
  @Test
  def testBracesAndParentheses() {
    val arr = Array(1, 2, 3)

    /**
     * 如下是相同的
     */
    arr.map(x => println(x))
    arr.map { x => println(x) }
  }

  @Test
  def testBracesAndParentheses2() {
    val arr = Array(2)

    /**
     * arr.map(case 2=>"Ok") does NOT work
     * 原因case 2 => "OK" 不是一段lambda表达式(也就是说它不是函数)，它是一段模式匹配语句
     */
    /*arr.map(case 2=>"Ok")*/

    /**
    花括号可行的原因是arr.map{},实际上是arr.map {...}，把{...}作为一个函数字面量处理,比如map{x=>x+1}

    稍微有点基础的话，会清楚方法的花括号有2种意思：
   1）scala中函数的小括号，可以用花括号来表示，即foo{xx} 与 foo(xx)是一回事儿。
  2）对于只有一个参数的方法，其小括号是可以省略的，map(lambda)可写为 map lambda，即这块case 2 => "OK" 连同花括号整体是一个lambda(函数字面量)。

这儿显然是第2个(追究原因就要看编译器在语法解析式的优先级了，
    看样子把花括号对待为lambda字面量的一部分要高于把花括号当作小括号来对待)，那么为什么加了花括号的{case 2 => "OK" }就可以当作一段函数字面量？

这要引出偏函数的概念，所谓偏函数(也叫部分函数)与完全函数想对应，普通的方法都是完全函数，即 f(i:Int) = xxx 是将所有Int类型作为参数的，是对整个Int集的映射；而偏函数则是对部分数据的映射，比如上面{case 2=> "OK" }就仅仅只对2做了映射。偏函数的实现都是通过模式匹配来表达的。


      */
    arr.map { case 2 => "Ok" }
  }


  /**
   *scala里面大小括号并不是一回事儿，虽然说很多时候可以替换。
   * 大括号之所以在小括号的地方能使用，是因为该小括号仅需一个参数，故小括号可以省略，
   * 而大括号的内容最后会被evaluate成一个结果，并作为小括号的参数给予传递。两个参数的小括号就无法直接用大括号替代。
   */
  @Test
  def testBracesAndParentheses3() {


  }

}
