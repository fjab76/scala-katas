package fjab.challenge.old

import fjab.challenge.old.ChessKnight._
import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class ChessKnightTest extends FunSpec {

  describe("Recursive solution") {
    it("should produce an empty list for position (0,0)") {
      assert(recursiveSolution(0, 0) == List())
    }

    it("should produce List((2,1),(1,2)) for position (3,3)") {
      assert(recursiveSolution(3,3) == List((2,1),(1,2)))
    }

    it("should produce List((2,1), (2,1), (1,2), (1,-2), (2,-1)) for position (8,1)") {
      assert(recursiveSolution(8,1) == List((2,1), (2,1), (1,2), (1,-2), (2,-1)))
    }
  }

  describe("Tail Recursive solution") {
    it("should produce an empty list for position (0,0)") {
      assert(tailRecursiveSolution(0, 0) == List())
    }

    it("should produce List((2,1),(1,2)) for position (3,3)") {
      assert(tailRecursiveSolution(3,3) == List((2,1),(1,2)))
    }

    it("should produce List((2,1), (2,1), (1,2), (1,-2), (2,-1)) for position (8,1)") {
      assert(tailRecursiveSolution(8,1) == List((2,1), (2,1), (1,2), (1,-2), (2,-1)))
    }
  }

}
