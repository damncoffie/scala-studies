package lectures.part1basics

object DefaultArgs extends App {

  // 1. Default arguments
  def trFact(n: Int, acc: Int = 1): Int = { // 1 is a default acc
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10WithDefaultAcc = trFact(10)
  val fact10 = trFact(10, 2)

  // 2. Named arguments
  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving")
  // savePicture(10) // compiler can't understand which parameter we want to put

  /*
  Solutions:
    1. pass in every leading argument
    2. name the arguments
   */
  savePicture(width = 10)
  savePicture(height = 10, width = 11, format = "bmp") // order doesn't matter this way
}
