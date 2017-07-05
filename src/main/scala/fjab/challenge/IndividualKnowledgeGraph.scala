package fjab.challenge

/**
 *
 */
abstract class IndividualKnowledgeGraph[T] {

  type Path = List[T]

  def findPathToExploreAllVertices(from: T)(n: Int, m: Int): Path = {

    def isSolution(numExploredVertices: Int) = numExploredVertices == n * m

    def traverseGraph(verticesAhead: List[Path]): Path = verticesAhead match{
      case Nil => Nil
      case pathToCurrentVertex :: rest =>
        if(isSolution(pathToCurrentVertex.size)) pathToCurrentVertex
        else {
          val adjacentVertices = adj(pathToCurrentVertex.head).filterNot(pathToCurrentVertex.contains(_))
          val pathsToAdjacentVertices = adjacentVertices.map(vertex => vertex :: pathToCurrentVertex)
          traverseGraph(addAdjPaths(rest,pathsToAdjacentVertices))
        }
    }

    traverseGraph(List(List(from))) match{
      case Nil => Nil
      case path => path.reverse
    }

  }

  def adj(vertex: T): List[T]
  def addAdjPaths(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path]

}
