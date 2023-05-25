package lectures.part2oop

object OOBasics extends App {

  val person = new Person
  println(person)

  val animal = new Animal("Jack", 2)
  println(animal.age)

  val greeter = new Greeter("John", 55)
  println(greeter.x)
  println(greeter.name) // will be printed even though there's no explicit filed like in Java
  greeter.greet("Alejandro")
  greeter.greet()

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print


  class Person

  class Animal(name: String, val age: Int)
  // class parameters are not FIELDS
  // to make a parameter a field use "val"

  class Greeter(val name: String, age: Int) {
    // body
    // can do anything that you can do in a code blcok
    val x = 2 // field

    // all expressions will be evaluated when new instance is created
    println(1 + 3)

    // method
    def greet(name: String): Unit = println(s"${this.name} says: " + "Hi " + name)

    // overloading (same name - different signatures)
    // dif signatures = dif numbers of params, or params with dif types + diff return types
    // Even if the return types are different, two methods with same signatures will throw compile-time error.
    def greet(): Unit = println(s"Hi, I am $name")
    // def greet(): Int = 42 // compile error - not clear which version of greet to call

    // multiple constructors
    // mostly used for using default parameters, but it's easily supplied by default values (val age: Int = 0)
    def this(name: String) = this(name, 0)

    def this() = this("John Doe")
  }

  // exercise

  class Writer(name: String, surname: String, val year: Int) {
    def fullName(): String = s"$name $surname"
  }


  class Novel(name: String, yearOfRelease: Int, author: Writer) {
    def authorAge: Int = yearOfRelease - author.year

    def isWrittenBy(author: Writer): Boolean = author == this.author

    def copy(newYearOfRelease: Int): Novel =
      new Novel(name, newYearOfRelease, author)
  }


  class Counter(val count: Int = 0) {

    // method!
    def inc1 = new Counter(count + 1) // immutability

    // overload
    def inc1(n: Int) = new Counter(count + n)

    // if we want to do something along with each increment - log in this case
    // and to not repeat ourselves
    def inc = {
      println(this)
      println("incrementing")
      new Counter(count + 1)
    }

    def inc(n: Int): Counter = {
      if (n <= 0) this
      else inc.inc(n - 1) // new instance each time
    }

    def dec = {
      println("decrementing")
      new Counter(count - 1)
    }

    def dec(n: Int): Counter = {
      if (n <= 0) this
      else dec.dec(n - 1)
    }

    def print = println(count)
  }

}
