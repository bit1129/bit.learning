package com.bit.basic

import org.junit.Test

import scala.collection.mutable.ArrayBuffer


class InFixTest {
  /** 因为在scala中1,2是对象，+是方法调用，所以 1 + 2就是最基本的中缀表达式 */
  @Test
  def testInFix(): Unit = {
    val a = 1 + 2
  }

  /** 第二种中缀表达式，对象 方法 参数 */
  @Test
  def testInFix2(): Unit = {
    val a = this inc 2
    Console println a
  }

  /**
   * :: Adds an element at the beginning of this list.
   * 结果：List(4, 3, 1, 2)
   */
  @Test
  def testInfix3(): Unit = {
    val b = 4 :: 3 :: List(1, 2)
    println(b)
  }

  @Test
  def testInfix4(): Unit = {
    object Cache {
      val cache = new ArrayBuffer[String]()

      def >>:(data: String): this.type = {
        cache += data
        this
      }

      def print1(): Unit = {
        cache.foreach(x => print(x + " "))
      }
    }

    val cache = "DEF" >>: "ABC" >>: Cache

    //ABC DEF
    cache.print1()
  }


  @Test
  def testInfix5(): Unit = {

    /**
     * 声明变量类型时，也可以用 A Foo B 中缀形式来表达，Foo也称为中缀类型
     * Foo怎么用？ 具有两个参数的Case Class是中缀类型
     * @tparam A
     * @tparam B
     */
    class InfixClass[A, B]
    var a: Int InfixClass String = null;

  }

  @Test
  def testInfix6(): Unit = {

    /**
     * 声明变量类型时，也可以用 A Foo B 中缀形式来表达，Foo也称为中缀类型
     * Foo怎么用？
     */
    case class InfixCaseClass(val a: Int, val b: Int)
    val x = InfixCaseClass(3, 4)
    x match {
      case 3 InfixCaseClass  4 => println("matched")
      case _ => println("Unmatched")
    }
  }
  def inc(x: Int) = x + 1


}
