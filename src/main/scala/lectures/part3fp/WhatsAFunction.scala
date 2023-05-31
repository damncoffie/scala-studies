package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder = new Function2[Int, Int, Int] { // type of adder is ((Int, Int) => Int) - syntactic sugar
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  // exercises

  // 1, concat function
  val stringConcat = new Function2[String, String, String] {
    override def apply(s1: String, s2: String): String = s1 + s2
  }

  // 2. define a function which takes an int and returns another function which takes an int a returns int
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function (called with multiple parameter lists, returns functions)

  // lambda style
  val lambdaSuperAdder = (x: Int) => (y: Int) => x + y
  println(lambdaSuperAdder(3)(4)) // curried function (called with multiple parameter lists, returns functions)
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
