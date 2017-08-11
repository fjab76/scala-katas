package fjab.challenge.graph.list

import fjab.challenge.graph._

/**
 * Given a finite chessboard and a knight, calculate the shortest list of moves to reach a given position on the board
 */
class ChessKnightFinite(to: Coordinate)(x: Int, y: Int) extends GraphTraverser[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  def findShortestPathFrom(from: Coordinate): Path = findPath(List(List(from)))

  override def neighbours(coordinate: Coordinate): List[Coordinate] = {
    val list = new scala.collection.mutable.ListBuffer[Coordinate]()
    moves.foreach{ move =>
      val (v, w) = coordinate + move
      if(v >= 0 && v < x && w >=0 && w<y)
        list += ((v,w))
    }
    list.toList
  }

  /**
   * The nature of the problem requires a breadth-first search in order to find the shortest path
   */
  override def addPathsToNeighbours(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path] =
    listOfPaths ++ pathsToAdjacentVertices

  override def isSolution(path: Path): Boolean = path.head == to

  override def isVertexEligibleForPath(vertex: Coordinate, path: Path): Boolean = !path.contains(vertex)
}
