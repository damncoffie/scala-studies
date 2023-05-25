package lectures.part1basics

object StringOps extends App {

  // Scala specific stuff

  // -
  val numberString = "42"
  println(numberString.toInt + 2) // .toInt

  // -
  println('a' +: numberString :+ 'z') // prepend/append

  // -
  val str = "Hello string"
  println(str.take(2)) // he (not sure if really Scala specific)

  // -
  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, I'm $name and I'm $age years old"
  val anotherGreeting = s"Hello, I'm $name and I'm going to be ${age + 1} years old"

  println(anotherGreeting)

  // -
  // F-interpolators
  val speed = 1.5f
  val myth = f"$name%s can eat $speed%2.2f" // 2 chars total, 2 signs after dot
  println(myth)

  // -
  // raw-interpolator

  // ignores escaping in raw string
  println(raw"This is a \n newline")

  // but will use escape if it passed by val
  val escaped = "This is a \n newline"
  println(escaped)
}
