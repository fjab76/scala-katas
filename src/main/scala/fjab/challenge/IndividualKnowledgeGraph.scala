package fjab.challenge

/**
 *
 */
abstract class IndividualKnowledgeGraph[T] {

  type Path = List[T]

  def findPathToExploreAllVertices(initial: List[Path]): Path = {

    def traverseGraph(verticesAhead: List[Path]): Path = verticesAhead match{
      case Nil => Nil
      case pathToCurrentVertex :: rest =>
        if(isSolution(pathToCurrentVertex)) pathToCurrentVertex
        else {
          val adjacentVertices = adj(pathToCurrentVertex.head).filter(filterVertex(_, pathToCurrentVertex))
          val pathsToAdjacentVertices = adjacentVertices.map(_ :: pathToCurrentVertex)
          traverseGraph(addAdjPaths(rest,pathsToAdjacentVertices))
        }
    }

    traverseGraph(initial) match{
      case Nil => Nil
      case path => path.reverse
    }

  }

  def adj(vertex: T): List[T]
  def addAdjPaths(listOfPaths: List[Path], pathsToAdjacentVertices: List[Path]): List[Path]
  def isSolution(path: Path): Boolean
  def filterVertex(vertex: T, path: Path): Boolean

}
