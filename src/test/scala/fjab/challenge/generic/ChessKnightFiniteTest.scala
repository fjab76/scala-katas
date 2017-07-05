package fjab.challenge.generic

import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class ChessKnightFiniteTest extends FunSpec {

  describe("The solution") {
    it("should be List((0,0)) for path (0,0)->(0,0)") {
      assert(new ChessKnightFinite((0,0))(8,8).findShortestPathFrom((0,0)) == List((0,0)))
    }

    it("should be List((2,1), (3,3)) for path (0,0)->(3,3)") {
      assert(new ChessKnightFinite((3,3))(8,8).findShortestPathFrom((0,0)) == List((0,0), (2,1), (3,3)))
    }

    ignore("should be List() for path (0,0)->(8,1)") {
      assert(new ChessKnightFinite((8,1))(8,8).findShortestPathFrom((0,0)) == List())
    }

    it("should be List((3,3), (5,4)) for path (1,2)->(5,4)") {
      assert(new ChessKnightFinite((5,4))(8,8).findShortestPathFrom((1,2)) == List((1, 2), (3,3), (5,4)))
    }
  }

}
