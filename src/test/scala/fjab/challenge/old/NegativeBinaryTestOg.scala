package fjab.challenge.old

import fjab.challenge.old.NegativeBinaryOg._
import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class NegativeBinaryTestOg extends FunSpec {

  describe("Recursive solution"){
    it("should produce an empty list for number 0"){
      assert(recursiveSolution(0) == List())
    }

    it("should produce List(1) for number 1"){
      assert(recursiveSolution(1) == List(1))
    }

    it("should produce List(1,1) for number -1"){
      assert(recursiveSolution(-1) == List(1,1))
    }

    it("should produce List(1,1,0,1) for number -9"){
      assert(recursiveSolution(-9) == List(1,1,0,1))
    }

    it("should produce List(1,0,0,1,1) for number 9"){
      assert(recursiveSolution(9) == List(1,0,0,1,1))
    }
  }

  describe("Tail Recursive solution"){
    it("should produce an empty list for number 0"){
      assert(tailRecursiveSolution(0) == List())
    }

    it("should produce List(1) for number 1"){
      assert(tailRecursiveSolution(1) == List(1))
    }

    it("should produce List(1,1) for number -1"){
      assert(tailRecursiveSolution(-1) == List(1,1))
    }

    it("should produce List(1,1,0,1) for number -9"){
      assert(tailRecursiveSolution(-9) == List(1,1,0,1))
    }

    it("should produce List(1,0,0,1,1) for number 9"){
      assert(tailRecursiveSolution(9) == List(1,0,0,1,1))
    }
  }


}
