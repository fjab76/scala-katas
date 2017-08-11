package fjab.challenge.graph.list

import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class NegativeBinaryTest extends FunSpec {

  describe("The solution"){
    it("should be List(0) for number 0"){
      assert(new NegativeBinary(0).findShortestBinaryRepresentation() == List(0))
    }

    it("should be List(1) for number 1"){
      assert(new NegativeBinary(1).findShortestBinaryRepresentation() == List(1))
    }

    it("should be List(0,1) for number -2"){
      assert(new NegativeBinary(-2).findShortestBinaryRepresentation() == List(0,1))
    }

    it("should be List(1,1) for number -1"){
      assert(new NegativeBinary(-1).findShortestBinaryRepresentation() == List(1,1))
    }

    it("should be List(1,1,0,1) for number -9"){
      assert(new NegativeBinary(-9).findShortestBinaryRepresentation() == List(1,1,0,1))
    }

    it("should be List(1,0,0,1,1) for number 9"){
      assert(new NegativeBinary(9).findShortestBinaryRepresentation() == List(1,0,0,1,1))
    }

  }


}
