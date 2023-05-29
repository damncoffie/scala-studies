package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
      /*
      A = Cat
      B = Dog = Animal
      */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList { // objects can't be parametrized
    def empty[A]: MyList[A] = ??? // public <T> MyList<T> empty() {...}
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. YES List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] // <? extends T> - producer
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) // ??? HARD QUESTION => we return a list of Animals

  // 2. NO List[Animal] is its own thing = INVARIANT
  class InvariantList[A] // <T>
  val invariantAnimal: InvariantList[Animal] = new InvariantList[Animal]
  // val invariantAnimal: InvariantList[Animal] = new InvariantList[Cat] - nope

  // 3. No-no-no = CONTRAVARIANCE
  class Trainer[-A] // <? super T>
  val trainer: Trainer[Cat] = new Trainer[Animal]


  // bounded types
  class Cage[A <: Animal] (animal: A) // <? extends Animal>
  val cage = new Cage(new Dog)

  class Car
  // generic type needs proper bounded type
  // val newCage = new Cage(new Car) // compile error



}

























