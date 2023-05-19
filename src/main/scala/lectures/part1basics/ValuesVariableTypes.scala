package lectures.part1basics

object ValuesVariableTypes extends App {

  val x: Int = 42
  val x1 = 42 // types are optional - compiler can infer them


  // x = 12; // vals are immutable (kind of similar to Java' final)

  val aString: String = "hello Scala" // semicolons are optional if it's on expression per line
  val aString1: String = "hello Scala"; val aString2 = "goodbye" // otherwise - required (bad style though)

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val aShort: Short = 1234
  val aLong: Long = 9231231231231129l
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables

  var aVariable = 12

  aVariable = 21 // side effects

}
