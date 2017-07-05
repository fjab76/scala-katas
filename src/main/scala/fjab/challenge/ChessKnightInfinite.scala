package fjab.challenge

/**
 * Given an infinite chessboard and a knight, calculate the shortest list of moves to reach a given position on the board
 */
class ChessKnightInfinite extends CollectiveKnowledgeGraph[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  override def adj(coordinate: Coordinate): List[Coordinate] = {
    val list = new scala.collection.mutable.ListBuffer[Coordinate]()
    moves.foreach{ tuple =>
      list += (coordinate + tuple)
    }
    list.toList
  }

  /**
   * The nature of the problem requires a breadth-first search in order to find the shortest path.
   * Observation: in an infinite chess board, only a search for the shortest path is viable
   */
  override def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point] =
    list ++ adjacentPoints
}
