package com.bit.basic


object Test20160403 {
  def main(args : Array[String]): Unit = {
    val v1 = "abc"
    val v2 = "";
    val v3 :String= null;
    println(Option(v1).filter(_.nonEmpty).getOrElse("XYZ"))
    println(Option(v2).filter(_.nonEmpty).getOrElse("DEF"))
    println(Option(v3).filter(_.nonEmpty).getOrElse("MSN"))

  }
}
