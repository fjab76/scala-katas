package fjab.challenge.graphs

import fjab.graphs.Graph
import org.scalatest.FunSuite

/**
 * Created by franciscoalvarez on 23/06/2017.
 */
class GraphTest extends FunSuite{

  test("print graph"){
    println(new Graph(List((0,1))))
  }
}
