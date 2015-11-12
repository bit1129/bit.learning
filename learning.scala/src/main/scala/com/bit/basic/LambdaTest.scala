package com.bit.basic

import org.junit.Test

class LambdaTest {

  @Test
  def lambdaShortage(): Unit = {
    //http://hongjiang.info/scala-pitfalls-1/
    def speak2(f: Function1[String, Unit]) = f

    /**
     *
     * @param f  函数常量，又称为lambda表达式
     * @return 参数本身，即speak方法的返回值是一个函数
     */
    def speak(f: String => Unit) = f


    /**
     * 指定函数字面量的参数的类型
     */
    speak((s: String) => println(s))("X")


    /**
     * 函数字面量的参数的类型可以忽略，原因是通过speak方法的签名，可以自动推断出来，s的类型是String
     */
    speak(s => println(s))("A")

    /**
     * 背后其实是编译器支持lambda的“eta转换”，
     * 简单的说就是对于lambda表达式中只有一个参数，并且箭头右边的逻辑是对入参执行一个函数：即 x => g(x)，则可以简写为g
     */
    speak(println)("B")

    /**
     * 对于所有的 x=>g(x) 都可以用占位符的形式写为：g(_)，相当于省去了lambda表达式的入参和箭头部分，然后用占位符表示入参
     */
    speak(println(_))("C")

    /**
     * println _ 和println(_) 都是部分应用函数(partial applied function)的写法，当只有一个参数时，这两个写法是等价的。
     */
    speak(println _)("D")
  }

}
