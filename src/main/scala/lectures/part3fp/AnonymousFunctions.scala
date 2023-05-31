package lectures.part3fp

object AnonymousFunctions extends App {

  var doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // anonymous function (LAMBDA)
  val doubler2 = (x: Int) => x * 2 // => Int = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3 // --->:() => Int<--- return type, --->() => 3<--- definition

  // careful
  println(justDoSomething) // function itself: lectures.part3fp.AnonymousFunctions$$$Lambda$3/358699161@25618e91
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to // val n: Int => Int = (x: Int) => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b // val n: (Int, Int) => Int = (a, b) => a + b
}
