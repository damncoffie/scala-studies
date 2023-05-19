package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // expression
  println(x)

  println(2 + 3 * 5)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  var aVariable = 2
  aVariable += 3 // -= *= /= - side effects (changing a variable)
  println(aVariable)

  // 1 Instructions (DO) vs Expressions (VALUE)

  // 1.1  IF in Scala is an expression!
  val condition = true
  val aConditionedValue = if (condition) 5 else 3 // if expression (not instruction!)
  println(aConditionedValue)
  println(if (condition) 5 else 3)

  // 1.2 avoid loops (it's for imperative languages)
  // never do this
  // never right imperative style code in scala
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // EVERYTHING in Scala is an expression
  // expression is something that produces a value

  val aWierdValue = (aVariable = 1); // Unit == void (assigning variable is a side effect)
                                    // side effects are expressions which return Unit
  println(aWierdValue) // ()

  // while is a side effect
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }

  println(aWhile) // () - Unit

  // side effects: println(), whiles, reassigning
  // they are stuff from imperative programming. like instructions, but return Unit

  // 1.2 Code blocks

  val aCodeBlock = {
    val x = 1
    val y = 2

    if (x + y > 3) "hello" else "goodbye"
  }
  // evaluates to the last expression type - String here
}
