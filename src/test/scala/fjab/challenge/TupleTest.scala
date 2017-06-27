package fjab.challenge

import org.scalatest.FunSuite

/**
 * Created by franciscoalvarez on 24/06/2017.
 */
class TupleTest extends FunSuite{

  test("test sum"){
    assert((2,4) + (1,1) == (3,5))
  }

  test("test substract"){
    assert((2,4) - (1,1) == (1,3))
  }

}
