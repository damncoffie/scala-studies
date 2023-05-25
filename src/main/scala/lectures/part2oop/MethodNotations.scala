package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangoutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def learns(thing: String): String = s"$name is learning  $thing"
    def learnsScala: String = this learns("Scala") // infix operator notation - equivalent to this.learns("Scala") or mary learns "Scala"
    def apply(v: Int): String = s"$name watched $favoriteMovie $v times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation - syntactic sugar
  // works only with a single parameter method

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangoutWith tom) // hangoutWith yielding a third thing
  println(mary + tom) // hangoutWith yielding a third thing
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS

  // prefix notation
  val x = -1 // equivalent with 1,unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent



  // exercises
  // 1.
  println((mary + "the rockstar")())
  println((mary + "the rockstar").apply())
  val starMary = mary + "the rockstar"
  println(starMary.name)

  // 2
  println((+mary).age)

  // 3
  println(mary learnsScala)

  // 4
  println(mary(2))
}
