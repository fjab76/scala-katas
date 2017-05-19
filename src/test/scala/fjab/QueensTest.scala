package fjab

import org.scalatest.FunSuite
import fjab.Queens._

/**
 * Created by franciscoalvarez on 19/05/2017.
 */
class QueensTest extends FunSuite{

  test("8 queens"){
    val solution = queens(8)
    println(solution.length)
    solution foreach (println(_))
  }

  test("2 queens"){
    val solution = queens(2)
    println(solution.length)
    solution foreach (println(_))
  }

  test("4 queens"){
    val solution = queens(4)
    println(solution.length)
    solution foreach (println(_))
  }

  test("5 queens"){
    val solution = queens(5)
    println(solution.length)
    solution foreach (println(_))
  }

}
