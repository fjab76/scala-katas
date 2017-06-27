package fjab.challenge.graphs

import fjab.graphs.{ Graph2, Graph }
import org.scalatest.FunSuite

/**
 * Created by franciscoalvarez on 23/06/2017.
 */
class GraphTest2 extends FunSuite{

  test("print graph"){
    println(new Graph2(List((0,1),(1,2),(0,3))))
  }

  test("exist path"){
    println(new Graph2(List((0,1),(1,2),(0,3),(4,5))).existPath(0,4))
  }

  test("find path"){
    println(new Graph2(List((0,1),(1,2),(0,3),(4,5))).findPath(0,0))
  }
}
