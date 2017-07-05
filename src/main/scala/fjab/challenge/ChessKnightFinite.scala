package fjab.challenge

/**
 * Given a finite chessboard and a knight, calculate the shortest list of moves to reach a given position on the board
 */
class ChessKnightFinite(x: Int, y: Int) extends CollectiveKnowledgeGraph[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  override def adj(coordinate: Coordinate): List[Coordinate] = {
    val list = new scala.collection.mutable.ListBuffer[Coordinate]()
    moves.foreach{ tuple =>
      val (v, w) = coordinate + tuple
      if(v >= 0 && v < x && w >=0 && w<y)
        list += ((v,w))
    }
    list.toList
  }

  /**
   * The nature of the problem requires a breadth-first search in order to find the shortest path
   */
  override def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point] =
    list ++ adjacentPoints //breadth-first search
}
