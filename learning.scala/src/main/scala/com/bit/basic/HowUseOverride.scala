package com.bit.basic

abstract class Parent {
  def speak

  def defaultSpeak(): Unit = {
    println("Parent is default speaking")
  }
}

class Child extends Parent {
  //如果parent是抽象方法，那么覆写时，需要指定override关键字
  def speak: Unit = println("Child is speaking")

  //如果parent是具体的方法，那么覆写时，需要指定override关键字
  override def defaultSpeak(): Unit = {
    println("Child is default speaking")
  }
}

