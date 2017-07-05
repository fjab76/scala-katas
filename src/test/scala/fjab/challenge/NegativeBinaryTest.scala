package fjab.challenge


import org.scalatest.FunSpec

/**
 * Created by franciscoalvarez on 04/06/2017.
 */
class NegativeBinaryTest extends FunSpec {

  describe("The solution"){
    it("should be an empty list for number 0"){
      assert(NegativeBinary.findBinaryRepresentation(0) == List())
    }

    it("should be List(1) for number 1"){
      assert(NegativeBinary.findBinaryRepresentation(1) == List(1))
    }

    it("should be List(1,1) for number -1"){
      assert(NegativeBinary.findBinaryRepresentation(-1) == List(1,1))
    }

    it("should be List(1,1,0,1) for number -9"){
      assert(NegativeBinary.findBinaryRepresentation(-9) == List(1,1,0,1))
    }

    it("should be List(1,0,0,1,1) for number 9"){
      assert(NegativeBinary.findBinaryRepresentation(9) == List(1,0,0,1,1))
    }

    it("calc"){
      val l = List(1,0,0,1,1)
      //assert(l.fold(0)((x,y) => x + y) == 9)

      var total = 0
      for(n <- l.indices){
        total += l(n) * BigInt(-2).pow(n).toInt
      }

      assert(total == 9)
    }
  }


}
