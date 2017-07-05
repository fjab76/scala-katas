package fjab.challenge

/**
 *
 */
abstract class CollectiveKnowledgeGraph[T] {

  type Path = List[T]

  def findPath(from: T, to: T): Path = {

    def isSolution(vertex: T) = vertex == to

    def traverseGraph(pathsAhead: List[Path], exploredVertices: Set[T]): Path = pathsAhead match{
      case Nil => Nil
      case pathToCurrentVertex :: rest =>
        if(isSolution(pathToCurrentVertex.head)) pathToCurrentVertex
        else {
          val adjacentVertices = adj(pathToCurrentVertex.head).filterNot(exploredVertices.contains(_))
          val pathsToAdjacentVertices = adjacentVertices.map(vertex => vertex :: pathToCurrentVertex)
          traverseGraph(addAdjPaths(rest,pathsToAdjacentVertices), exploredVertices + pathToCurrentVertex.head)
        }
    }

    traverseGraph(List(List(from)), Set()) match{
      case Nil => Nil
      case path => path.reverse
    }
  }


  def adj(vertex: T): List[T]
  def addAdjPaths(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path]

}
