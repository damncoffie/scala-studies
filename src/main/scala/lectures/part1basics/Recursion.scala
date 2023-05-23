package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  // 1. boring, enormous stack, space-greedy recursion
  def factorial(v: Int): Int = {
    if (v == 1) v
    else {
      println("Computing factorial of " + v + ". Need factorial of " + (v - 1) + " first.")
      val result = v * factorial(v - 1)
      println("Computed factorial of " + v + " - " + result)

      result
    }
  }

  // stack overflow error
  //println(factorial(5000))

  // 2. functional-style, space-constant, stack-reusing tail recursion
  // good explanation - https://maxglassie.github.io/2017/08/24/tail-recursion.html
  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, acc: BigInt): BigInt = {
      if (x == 1) acc
      else factHelper(x - 1, x * acc) // TAIL RECURSION = use recursive call as a LAST expression
    }

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  // 3. Examples

  // WHEN YOU NEED LOOPS USE _TAIL_ RECURSION.

  // 3.1 Concatenate string n times

  // Ver 1
  def concatString(str: String, n: Int): String = {
    def concatHelper(s: String, result: String, count: Int): String = {
      if (count == 1) result
      else concatHelper(s, result + s, count - 1)
    }

    concatHelper(str, str, n)
  }

  println("Ver 1 " + concatString("hello", 6))

  // Ver 2
  def concatString2(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatString2(aString, n - 1, accumulator + aString)
  }

  println("Ver 2 " + concatString2("hello", 4, ""))

  // 3.2 isPrime

  // 3.3 fibonacci
  def fibonacci(n: Int): Int = {
    def fibHelper(i: Int, last: Int, nextToLast: Int): Int = { // two accumulators: last = (n - 1), nextToLast = (n - 2)
      if (i >= n) last
      else fibHelper(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fibHelper(3, 1, 1)
  }

  /* count, n1, n2
  8 0 1
  7 1 1
  6 1 2
  5 2 3
  4 3 5
  3 5 8
  2 8 13
  1 13 21
  0 21 34
  */
  def fibonacci2(n: Int): BigInt = {
    def fibh(count: Int, n1: BigInt, n2: BigInt): BigInt = {
      println(count + " " + n1.toString() + " " + n2.toString())
      if (count == 0)
        n1
      else {
        fibh(count - 1, n2, n1 + n2) // n2 in place of n1, sum in place of n2
      }
    }

    fibh(n, 0, 1)
  }

  println(fibonacci2(8)) // 0 1 1 2 3 5 8 13 21 34 55
}
