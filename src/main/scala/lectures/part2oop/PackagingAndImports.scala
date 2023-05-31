package lectures.part2oop

import playground.{PrinceCharming, Cinderella as Princess}

import java.sql
import java.util.Date
import java.sql.{Date => SqlDate}
// import playground._ import everything

//import playground.Cinderella

object PackagingAndImports extends App {

  // package members are accessible by their name
  val writer = new Writer("Alan", "Wake", 2023)

  // import the package
  val princess = new Princess
  //val princess = new playground.Cinderella // fully-qualified name

  // packages are in hierarchy
  // matching folder structure.

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
