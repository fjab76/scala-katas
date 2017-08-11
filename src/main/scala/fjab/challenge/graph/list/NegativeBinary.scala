package fjab.challenge.graph.list

/**
 * Created by franciscoalvarez on 04/06/2017.
  *
  * Given an integer, calculate the shortest sequence of bits representing that number in base -2
  * In base -2, the sequence B of N bits represents the number
  * sum{B[i]*(-2).pow(i) for i=0...N-1}
  * The empty sequence represents 0
  *
  * With some imagination, the search for the solution to this problem can be equated to searching for a path in a
  * graph with the following characteristics:
  * - the vertices of this graph are 0s and 1s
  * - from any given vertex, it is possible to move only to a new 0 and a new 1 (new in the sense that those vertices
  * have not yet been visited). Because of this limitation on the allowed moves, there are never repeat vertices in
  * a path and as a result, it is not necessary to implement any extra filter on 'isVertexEligibleForPath'.
  * - another consequence of the previous point is that the graph is infinite.
  * - the succession of 0s and 1s in a path is interpreted as a binary representation
  *
 */
class NegativeBinary(int: Int) extends GraphTraverser[Int]{

  val moves: List[Int] = List(0, 1)

  def findShortestBinaryRepresentation() = findPath(List(List(0),List(1)))

  override def neighbours(vertex: Int): List[Int] = moves

  override def addPathsToNeighbours(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path] =
    listOfPaths ++ pathsToAdjacentVertices //breadth-first search

  override def isSolution(path: Path): Boolean = {
    val p = path.reverse
    var total = 0
    for(n <- p.indices){
      total += p(n) * BigInt(-2).pow(n).toInt
    }
    total == int
  }

  /**
    * By construction, a path can never visit the same vertex twice. Therefore, no extra filter is needed.
    */
  override def isVertexEligibleForPath(vertex: Int, path: Path): Boolean = true
}
