package lectures.part3fp

object TuplesAndMaps extends App {

  // 1. Tuple = finite ordered "lists"
  /*
    Similar to lists or arrays, but unlike lists and arrays, tuples can contain elements of different types.
    Tuples provide a convenient way to group multiple values together into a single object.
  */
  val aTuple = Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2 - access element
  println(aTuple(1)) // 2 - also access
  println(aTuple.copy(_2 = "miss you Java")) // (2,miss you Java)
  println(aTuple.swap) // ("hello, Scala", 2)
  println(Tuple5(12, 1.5f, "hey", 't', null))

  // 2. Map - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), ("Daniel", 789)).withDefaultValue(-1)
  val sugarPhoneBook = Map("Jim" -> 555, "Daniel" -> 789) // -> some sugar
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim")) // true
  println(phoneBook("Jim")) // 555
  println(phoneBook("Mary")) // NoSuchElement exception if no .withDefaultValue(...)

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook) // Map(Jim -> 555, Daniel -> 789, Mary -> 678)

  // functions on maps

  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // Map(jim -> 555, daniel -> 789)

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap) // Map(Jim -> 555)

  // mapValues
  println(phoneBook.view.mapValues(number => "+48 " + number).toMap) // Map(Jim -> +48 555, Daniel -> +48 789)

  // conversions to other collections
  println(phoneBook.toList) // List((Jim,555), (Daniel,789))
  println(List(("Daniel", 555)).toMap) // Map(Daniel -> 555)

  val names = List("John", "James", "Harry", "Henry", "Michael")
  println(names.groupBy(name => name.charAt(0))) // HashMap(J -> List(John, James), M -> List(Michael), H -> List(Harry, Henry))

  // exercises
  /*
    1. "Jim" -> 555 and "JIM" -> 900 use (pair => pair._1.toLowerCase -> pair._2) - ???
      carefull with keys
    2. Social network
  */

  // 1
  val phoneBook4 = Map(("Jim", 555), ("JIM", 789)).withDefaultValue(-1)
  println(phoneBook4.map(pair => pair._1.toLowerCase -> pair._2)) // Map(jim -> 789) - lose some data

  // 2
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  // add to friends mutually
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  // unfriend mutually
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]) : Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))
}
