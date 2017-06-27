package fjab.challenge

import fjab.challenge.KnightsTour
import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class KnightsTourTest extends FunSpec {

  describe("Recursive solution") {
    it("should produce an empty list for position (0,0)") {
      val result = new KnightsTour(4,4).findPathToExploreAllVertices((0,0))(4,4)
      assert(result == List())
    }

    it("should produce a solution for initial position (0,0) and board 8x8") {
      val result = new KnightsTour(8,8).findPathToExploreAllVertices((0,0))(8,8)
      assert(result.size == 63)
      assert(result == List((2,1), (2,1), (2,1), (1,2), (-1,2), (-2,-1), (-2,1), (-2,-1), (1,-2), (2,1), (2,1), (2,1), (-1,-2), (-1,2), (-2,-1), (-2,1), (-1,-2), (2,1), (2,1), (1,-2), (2,1), (-1,-2), (-2,1), (2,1), (-1,-2), (-2,-1), (-1,2), (1,2), (-2,-1), (-1,-2), (1,-2), (1,2), (-2,-1), (1,-2), (2,-1), (-1,2), (-1,-2), (-1,2), (2,1), (2,1), (-1,-2), (1,-2), (2,1), (1,2), (-2,-1), (2,-1), (-2,-1), (-2,1), (1,2), (1,-2), (2,-1), (-1,2), (1,2), (-2,-1), (2,-1), (-1,-2), (-2,1), (-2,-1), (-2,1), (1,2), (2,1), (-2,1), (-1,2)))
    }
  }




}
