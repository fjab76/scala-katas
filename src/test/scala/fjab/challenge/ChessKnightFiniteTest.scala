package fjab.challenge

import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class ChessKnightFiniteTest extends FunSpec {

  describe("The solution") {
    it("should be an empty list for path (0,0)->(0,0)") {
      assert(new ChessKnightFinite(8,8).findPath((0, 0), (0,0)) == List())
    }

    it("should be List((2,1), (3,3)) for path (0,0)->(3,3)") {
      assert(new ChessKnightFinite(8,8).findPath((0, 0), (3,3)) == List((2,1), (3,3)))
    }

    it("should be List((2,1), (4,2), (5,4), (6,2), (8,1)) for path (0,0)->(8,1)") {
      assert(new ChessKnightFinite(8,8).findPath((0, 0), (8,1)) == List((2,1), (4,2), (5,4), (6,2), (8,1)))
    }

    it("should be List((3,3), (5,4)) for path (1,2)->(5,4)") {
      assert(new ChessKnightFinite(8,8).findPath((1, 2), (5,4)) == List((3,3), (5,4)))
    }
  }

}
