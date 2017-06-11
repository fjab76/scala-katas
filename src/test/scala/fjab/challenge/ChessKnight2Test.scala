package fjab.challenge

import fjab.challenge.ChessKnight2._
import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class ChessKnight2Test extends FunSpec {

  describe("Recursive solution") {
    it("should produce an empty list for position (0,0)") {
      assert(recursiveSolution(0,0)(4,4) == List())
    }
  }

  describe("Tail Recursive solution") {
    it("should produce a solution for initial position (0,0) and board 8x8") {
      assert(tailRecursiveSolution(0,0)(8,8) == List((2,1), (2,1), (2,1), (1,2), (-1,2), (-2,-1), (-2,1), (-2,-1), (1,-2), (2,1), (2,1), (2,1), (-1,-2), (-1,2), (-2,-1), (-2,1), (-1,-2), (2,1), (2,1), (1,-2), (2,1), (-1,-2), (-2,1), (2,1), (-1,-2), (-2,-1), (-1,2), (1,2), (-2,-1), (-1,-2), (1,-2), (1,2), (-2,-1), (1,-2), (2,-1), (-1,2), (-1,-2), (-1,2), (2,1), (2,1), (-1,-2), (1,-2), (2,1), (1,2), (-2,-1), (2,-1), (-2,-1), (-2,1), (1,2), (1,-2), (2,-1), (-1,2), (1,2), (-2,-1), (2,-1), (-1,-2), (-2,1), (-2,-1), (-2,1), (1,2), (2,1), (-2,1), (-1,2)))
    }

    it("should produce a solution for initial position (0,0) and board 6*6") {
      assert(tailRecursiveSolution(0,0)(6,6) == List((2,1), (2,1), (1,2), (-2,1), (-2,-1), (-1,-2), (2,1), (2,1), (-2,1), (-2,-1), (1,-2), (1,2), (-2,1), (1,-2), (-1,-2), (2,-1), (2,1), (1,2), (-1,2), (-1,-2), (2,-1), (-1,-2), (-1,2), (-2,-1), (-1,2), (1,2), (2,-1), (2,1), (-1,-2), (1,-2), (-2,-1), (-1,2), (-1,-2), (2,1), (2,-1)))
    }

    it("should produce a solution for initial position (0,0) and board 5*6") {
      val result = tailRecursiveSolution(0,0)(5,6)
      assert(result.size == 29)
      assert(result == List((2,1), (2,-1), (-1,2), (1,2), (-2,1), (-2,-1), (1,-2), (2,1), (1,2), (-2,-1), (-2,1), (1,-2), (2,1), (-2,1), (-1,-2), (1,-2), (2,-1), (1,2), (-2,1), (1,2), (-2,-1), (-1,-2), (1,-2), (2,1), (1,2), (-2,-1), (-2,-1), (2,-1), (2,1)))
    }

    it("should produce a solution for initial position (1,1) and board 5*6") {
      val result = tailRecursiveSolution(1,1)(5,6)
      assert(result.size == 29)
      assert(result == List((2,1), (1,-2), (-2,1), (-2,-1), (1,2), (-1,2), (2,1), (2,-1), (-2,-1), (1,2), (-2,-1), (-1,-2), (1,-2), (2,1), (1,2), (-2,-1), (1,-2), (1,2), (-1,2), (-2,1), (-1,-2), (2,1), (2,1), (-1,-2), (1,-2), (-2,-1), (-2,1), (1,2), (-1,2)))
    }

    it("should produce a solution for initial position (0,0) and board 6*5") {
      val result = tailRecursiveSolution(0,0)(6,5)
      assert(result.size == 29)
      assert(result == List((2,1), (2,1), (1,2), (-2,-1), (-2,1), (-1,-2), (1,-2), (1,2), (1,2), (-2,-1), (-1,-2), (2,-1), (2,1), (1,2), (-2,-1), (1,-2), (1,2), (-1,2), (-2,-1), (-2,1), (1,-2), (1,2), (-2,-1), (1,-2), (2,-1), (2,1), (-1,2), (-1,-2), (2,-1)))
    }

  }

}
