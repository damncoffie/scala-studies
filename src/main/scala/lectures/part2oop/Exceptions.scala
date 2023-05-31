package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // npe ^

  // 1. throwing exceptions

  //  val aWierdValue: String = throw new NullPointerException // aWierdValue is of type Nothing

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42
  }

  val potentialFail = try {
    // code that might fail
    getInt(false)
  } catch {
    case e: NullPointerException => println("caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  // 3. how to define custom exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception



  // exercises

  // out of memory (OOM)
  // val array = Array.ofDim[Int](Int.MaxValue)

  // stackoverflow (SO)
  //def infinite: Int = 1 + infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
  }
}
