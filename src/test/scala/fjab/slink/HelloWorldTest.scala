package fjab.slink

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import fjab.slinkc.HelloWorld.printHelloWorld

/**
 * Created by franciscoalvarez on 08/09/2016.
 */
@RunWith(classOf[JUnitRunner])
class HelloWorldTest extends FunSuite{

  test("testing infrastructure"){
    printHelloWorld()
    assert(true)
  }
}
