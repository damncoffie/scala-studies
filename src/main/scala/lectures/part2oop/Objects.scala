package lectures.part2oop

object Objects {

  // SCALA DOES MOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // do not receive parameters (like class) // type + its only instance

    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Richard")
  }
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println(mary == john) // true

  val person1 = new Person("Mary")
  val person2 = new Person("John")
  println(person1 == person2) // false

  val richard = Person(person1, person2)

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  def main(args: Array[String]): Unit = {

  }
  // or extends App

}
