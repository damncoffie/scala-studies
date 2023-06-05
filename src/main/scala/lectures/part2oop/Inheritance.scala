package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
     def eat = println("nomnom") // default modifier is public
  }

  class Cat extends Animal {
    def crunch = {
      eat // 2 ..but Cat can call it inside it
      println("crunch crunch")
    }
  }

  val cat = new Cat
  // cat.eat // 1 ..can't call it explicitly on Cat instance if protected
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)


  // overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
    // override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }
  // same thing as above
  /*
  class Dog(dogType: String) extends Animal {
    override val creatureType: String = dogType
  }
  */

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member.
  // 2 - use final on class itself (can't be extended at all).
  // 3 - seal the class = extends classes in THIS FILE, but prevent extension in other files
  // (if you want to be exhaustive in your class hierarchy).
}
