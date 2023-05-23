package lectures.part1basics

object CBNvsCBV extends App {

  // value is computed before call
  // same value used everywhere
  def calledByValue(x: Long): Unit = {
    println("Called by value: " + x) // 345873965583500
    println("Called by value: " + x) // 345873965583500
  }

  // expression is passed literally
  // expression is evaluated at every use within
  def calledByName(x: => Long): Unit = {
    println("Called by name: " + x) // 345874077107200
    println("Called by name: " + x) // 345874077982700
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
