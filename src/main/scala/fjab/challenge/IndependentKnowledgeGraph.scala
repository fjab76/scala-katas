package fjab.challenge

/**
 *
 */
abstract class IndependentKnowledgeGraph[T] {

  type Point = (T, List[T], Set[T])


  def findPathToExploreAllVertices(from: T)(n: Int, m: Int): List[T] = {

    def isSolution(numExploredVertices: Int) = numExploredVertices == n * m - 1

    def solution(verticesAhead: List[Point]): List[T] = verticesAhead match{
      case Nil => Nil
      case (currentVertex, pathToCurrentVertex, exploredVertices) :: rest =>
        if(isSolution(exploredVertices.size)) pathToCurrentVertex
        else {
          val adjacentVertices = adj(currentVertex).filterNot(exploredVertices.contains(_))
          val adjacentPoints = adjacentVertices.map(vertex => (vertex, vertex :: pathToCurrentVertex, exploredVertices + vertex))
          solution(addAdjPoints(rest,adjacentPoints))
        }
    }

    solution(List((from, List(), Set[T]()))) match{
      case Nil => Nil
      case path => path.reverse
    }

  }

  def adj(vertex: T): List[T]
  def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point]

}