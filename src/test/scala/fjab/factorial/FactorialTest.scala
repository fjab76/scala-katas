package fjab.factorial

import org.scalatest.FunSuite
import fjab.factorial.Factorial._

/**
 * Created by franciscoalvarez on 08/09/2016.
 */
class FactorialTest extends FunSuite{

  test("tail recursive method completes successfully"){
    tailRecursive(100000)
    assert(true)
  }

  test("not tail recursive method throws exception"){
    intercept[java.lang.StackOverflowError](
        notTailRecursive(100000)
    )
  }

}
