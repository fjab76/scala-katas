import org.scalatest.FunSuite
import fjab.OptimalChange._

/**
 * Created by franciscoalvarez on 30/10/2016.
 */
class OptimalChangeTest extends FunSuite{

  test("test greedy optimal change") {
    assert(greedyOptimalChange(List(1,4,6), 8) == List(1,1,6))
  }

  test("test optimal change") {
    assert(optimalChange(List(1,4,6), 8) == List(4,4))
  }

  test("test optimal number of coins") {
    assert(optimalNumberCoins(List(1,4,6), 8) == 2)
  }
}
