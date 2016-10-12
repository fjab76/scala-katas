package fjab.factorial

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import fjab.factorial.Factorial._

/**
 * Created by franciscoalvarez on 08/09/2016.
 */
@RunWith(classOf[JUnitRunner])
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
