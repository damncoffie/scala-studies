package playground

import lectures.part2oop.Generics.*

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String

  // higher-order function!
  // either receive function as params or returns them
  def map[B](transformer: A => B): MyList[B] // def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B] // def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A] // def filter(predicate: MyPredicate[A]): MyList[A]

  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def forEach(f: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length!")
    else Empty
  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  override def printElements: String = ""
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
    */
  override def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer.apply(head), t.map(transformer))
  }

  /*
  [1,2].flatMap(n => [n, n + 1])
  = [1,2] ++ [2].flatMap(n => [n, n + 1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,2,3]
  */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer.apply(h) ++ t.flatMap(transformer)
  }

  /*
  [1,2,3].filter(n % 2 == 0) =
    [2,3].filter(n % 2 == 0) =
    = new Cons(2, [3].filter(n % 2 == 0)
    = new Cons(2, Empty.filter(n % 2 == 0))
    = new Cons(2, Empty)
  */
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
  [1,2] ++ [3,4,5]
  = new Cons(1, [2] ++ [3,4,5])
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5))))
  */

  override def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  // ?
  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  // ??
  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  // ???
  /*
  [1,2,3].fold(0)(+) =
  = [2,3].fold(1)(+) =
  - [3].fold(3)(+)
  = [].fold(6)(+)
  */
  override def fold[B](start: B)(operator: (B, A) => B): B = { // aka reduce
    /*val newStart = operator(start, h)
    t.fold(newStart)(operator)*/

    t.fold(operator(start, h))(operator)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  // my clunky version
  /*override def filter(predicate: MyPredicate[A]): MyList[A] = {
    def filterHelper(current: MyList[A], accList: MyList[A]): MyList[A] = {
      if (current == Empty)
        println("if " + this.hashCode())
        accList
      else {
        println("else " + this.hashCode())
        if (predicate.test(current.head))
          filterHelper(current.tail, accList.add(current.head))
        else
          filterHelper(current.tail, accList)
      }
    }

    filterHelper(this, Empty)
  }*/

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h  ${t.printElements}"
}

object ListTest extends App {
  /*val list = new playground.Cons(1, new playground.Cons(2, new playground.Cons(3, playground.Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  println(list.toString)*/

  //

  /*var listOfIntegers: MyList[Int] = Empty
  var listOfStrings: MyList[String] = Empty

  listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  listOfStrings = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)*/

  //

  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers = new Cons(4, new Cons(5, Empty))

  // filter test
  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(v: Int): Boolean = v % 2 == 0
  }).toString)

  println(listOfIntegers.filter(v => v % 2 == 0).toString) // lambda style

  // map test
  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(a: Int): Int = a * 2
  }).toString)

  println(listOfIntegers.map(a => a * 2).toString) // lambda style

  // concatenation test
  println((listOfIntegers ++ anotherListOfIntegers).toString)

  // flatMap test
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)

  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString) // lambda style

  // forEach test
  listOfIntegers.forEach(println)

  // sort test
  println(listOfIntegers.sort((x, y) => y - x))

  // zipWith test
  val listOfStrings = new Cons("uno", new Cons("dos", Empty))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  // fold test
  println(listOfIntegers.fold(0)(_ + _))


  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations)
}
