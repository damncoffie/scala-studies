package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // 0. Seq
  /*
    Represent an ordered collection of elements.
  */
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // 3 - by index
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // 1. Ranges
  /*
    Ranges represent a sequence of numbers.
    Ranges can be inclusive or exclusive and can have a step value.
    Ranges are useful for iterating over a sequence of numbers.
  */
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // 2. List
  /*
    Lists are immutable sequences.
    Elements are stored in a linked list data structure.
    Lists support fast access to the head (first element) and tail (remaining elements).
  */
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89 // prepend also - ::
  println(prepended)

  val apple5 = List.fill(5)("apple")
  println(apple5)

  println(aList.mkString("-|-")) // mkString concatenates

  // 3. Array
  /*
    Arrays are mutable sequences.
    Elements are stored in a contiguous memory block.
    Arrays have a fixed size and support random access to elements.
  */
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntactic sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion(?)
  println(numbersSeq)

  // 4. Vectors
  /*
    Vectors are immutable sequences.
    Elements are stored in a tree structure.
    Vectors support efficient random access, concatenation, and splitting.

    Uses a form of tree called a "H-AMT" (Hash Array Mapped Trie) - hence random access
  */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)
  println("random access vector: " + vector(2))

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // copy with one replaced element
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList)) // 4300830.5

  // depth of the tree is small
  // need to replace an entire 32-element chunk
  println(getWriteTime(numbersVector)) // 1931.5
}
