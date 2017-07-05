package fjab.challenge

/**
 *
 */
abstract class HistoryAwareGraph[T] {

  type Point = (T, List[T]) //(current vertex, path to current vertex)

  protected def findPath(from: List[Point], to: T): List[T] = {

    def isSolution(vertex: T) = vertex == to

    def traverseGraph(verticesAhead: List[Point]): List[Point] = verticesAhead match{
      case Nil => Nil
      case (currentVertex, pathToCurrentVertex) :: rest =>
        if(isSolution(currentVertex)) verticesAhead
        else {
          val adjacentPoints = adj(currentVertex, pathToCurrentVertex)
          traverseGraph(addAdjPoints(rest,adjacentPoints))
        }
    }

    traverseGraph(from) match{
      case Nil => Nil
      case (v, r) :: _ => if(v == 0) Nil else r.reverse
    }
  }
  

  def adj(point: Point): List[Point]
  def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point]

}
