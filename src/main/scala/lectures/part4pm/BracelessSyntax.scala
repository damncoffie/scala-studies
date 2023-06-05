package lectures.part4pm

object BracelessSyntax extends App {

  // if - expressions
  val anIfExpressions = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger" // higher indentation than the if part
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  println(anIfExpression_v5)

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // fir comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double"
    case _ => "smth else"
  }

  // scala 3
  val aPatterMatch_v2 =
    meaningOfLife match
      case 1 => "the one"
      case 2 => "double"
      case _ => "smth else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40


    partialResult + 2

  println(computeMeaningOfLife(11))

  // class definition with significant indentation (same fo traits, objects, enums, etc)
  class Animal: // compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")
    end eat

    def grow(): Unit =
      println("I'm growing")

    // 3000 more lines of code
  end Animal // for if, match, for, methods, classe, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 space + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces
}
