package lectures.part1basics

object Functions extends App {

  // 1. function definition
  def aFunction(a: String, b: Int) : String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  // 2. scala loops with recursion
  def aRepeatedFunction(aString: String, count: Int) : String = {
    if (count == 1) aString else aString + aRepeatedFunction(aString, count - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION
  // best practice to specify return type of a function
  // (although for recursive it's a MUST, for non-recursive - OPTIONAL)

  // 3. Function with side effect

  def aFunctionWithSideEffect(): Unit = println("hello")

  // 4. Functions defined inside functions

  def aBigFunction(n: Int) = {
    def aSmallFunction(a: Int, b: Int) = a + b

    aSmallFunction(n, n - 1) // small function defines a return type of big one
  }

  // 5. Examples

  // 5.1 factorial
  def factorialFun(v: Int): Int = {
    if (v == 1) v
    else v * factorialFun(v - 1)
  }

  println(factorialFun(5)) // 1 * 2 * 3 * 4 * 5 = 120

  // 5.2 fibonacci
  def fibonacciFun(v: Int): Int = {
    if (v <= 1) v
    else
      println("v = " + v + " " +  (v - 1) + " - " + (v - 2))
      val res = fibonacciFun(v - 1) + fibonacciFun(v - 2)
      println("Claculated: " + res)
      res
  }

  println("Fibonacci: " + fibonacciFun(7)) // 0 1 1 2 3 5 8 13

  // 5. Test if prime
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(37)) // true
  println(isPrime(9)) // false
  println(isPrime(1003)) // false
}
