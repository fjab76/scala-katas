package fjab.challenge.specific

import scala.collection.mutable.ListBuffer

/**
 * Given a finite chessboard and a knight, calculate the shortest list of moves to reach a given position on the board
 */
class ChessKnightFinite(to: Coordinate)(x: Int, y: Int) extends GraphTraversable[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  def findShortestPathFrom(from: Coordinate): Path = findPath(new ListBuffer() += (List(from)))

  override def adjVertices(coordinate: Coordinate): List[Coordinate] = {
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
  override def addAdjPaths(listOfPaths: ListBuffer[Path], pathsToAdjacentVertices: List[Path]) =
    listOfPaths.appendAll(pathsToAdjacentVertices) //breadth-first search

  override def isSolution(path: Path): Boolean = path.head == to

  override def isVertexEligibleForPath(vertex: Coordinate, path: Path): Boolean = !path.contains(vertex)
}
