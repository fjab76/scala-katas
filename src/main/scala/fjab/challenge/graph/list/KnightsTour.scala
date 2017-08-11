package fjab.challenge.graph.list

import fjab.challenge.graph._

/**
  * Created by franciscoalvarez on 11/06/2017.
  *
  * Knight's tour problem, https://en.wikipedia.org/wiki/Knight%27s_tour
  * A knight's tour is a sequence of moves of a knight on a chessboard such that the knight visits
  * every square only once
  */
class KnightsTour(x: Int, y: Int) extends GraphTraverser[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  def findTour(from: Coordinate) = super.findPath(List(List(from)))

  override def neighbours(coordinate: Coordinate): List[Coordinate] = {
    val list = new scala.collection.mutable.ListBuffer[Coordinate]()
    moves.foreach{ tuple =>
      val (v, w) = coordinate + tuple
      if(v >= 0 && v < x && w >=0 && w<y)
        list += ((v,w))
    }
    list.toList
  }


  override def addPathsToNeighbours(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path] =
    pathsToAdjacentVertices ++ listOfPaths //depth-first search


  override def isSolution(path: Path): Boolean = path.length == x * y

  override def isVertexEligibleForPath(vertex: Coordinate, path: Path): Boolean = !path.contains(vertex)

}
