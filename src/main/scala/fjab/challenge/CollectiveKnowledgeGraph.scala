package fjab.challenge

/**
 *
 */
abstract class CollectiveKnowledgeGraph[T] {

  type Point = (T, List[T]) //(current vertex, path to current vertex)

  def findPath(from: T, to: T): List[T] = {

    def isSolution(vertex: T) = vertex == to

    def traverseGraph(verticesAhead: List[Point], exploredVertices: Set[T]): List[T] = verticesAhead match{
      case Nil => Nil
      case (currentVertex, pathToCurrentVertex) :: rest =>
        if(isSolution(currentVertex)) pathToCurrentVertex
        else {
          val adjacentVertices = adj(currentVertex).filterNot(exploredVertices.contains(_))
          val adjacentPoints = adjacentVertices.map(vertex => (vertex, vertex :: pathToCurrentVertex))
          traverseGraph(addAdjPoints(rest,adjacentPoints), exploredVertices + currentVertex)
        }
    }

    traverseGraph(List((from, List())), Set()) match{
      case Nil => Nil
      case path => path.reverse
    }
  }
  

  def adj(vertex: T): List[T]
  def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point]

}
