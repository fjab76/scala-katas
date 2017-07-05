package fjab.challenge.generic

/**
 *
 */
abstract class GraphTraversable[T] {

  type Path = List[T]

  /**
    * This method traverses all the possible paths of the graph, moving from one vertex to another, until it finds
    * the one that is being searched as per the definition of the method 'isSolution'.
    *
    * The algorithm to traverse the graph, depth-first or breadth-first, is determined by the method 'addAdjPaths'.
    *
    * The process to calculate the neighbours of a given vertex is specific for every type of graph and is implemented
    * by the method 'adjVertices'. The method 'isVertexEligibleForPath' filters the neighbours that are eligible to be
    * included in a path (for instance, in most cases it is desirable not to pass twice through the same vertex).
    *
    * It is worth noticing that the present algorithm to traverse the graph is very generic and therefore
    * sub-optimal for problems like finding the path between 2 vertices. The issue is that, with this algorithm, the
    * different paths do not share information about the vertices visited, resulting in many paths visiting vertices
    * that are already known not to be part of the solution.
    *
    *
    * @param seed Initial paths used as a seed to calculate the rest of the paths
    * @return Path The searched path or Nil if the desired path does not exist
    */
  def findPath(seed: List[Path]): Path = {

    def traverseGraph(verticesAhead: List[Path]): Path = verticesAhead match{
      case Nil => Nil
      case pathToCurrentVertex :: rest =>
        if(isSolution(pathToCurrentVertex)) pathToCurrentVertex
        else {
          val adjacentVertices = adjVertices(pathToCurrentVertex.head).filter(isVertexEligibleForPath(_, pathToCurrentVertex))
          val pathsToAdjacentVertices = adjacentVertices.map(_ :: pathToCurrentVertex)
          traverseGraph(addAdjPaths(rest,pathsToAdjacentVertices))
        }
    }

    traverseGraph(seed) match{
      case Nil => Nil
      case path => path.reverse
    }

  }

  /**
    * The implementation to calculate the adjacent vertices is dependant on the nature of the graph: moves allowed
    * from one vertex to another, constraints, etc.
    */
  def adjVertices(vertex: T): List[T]

  /**
    * This method adds the newly calculated paths to the neighbours, to the list of remaining paths to explore.
    * Depending on whether the new paths are added in front of the list or at the end, the resulting traversing
    * algorithm will be depth-first or breadth-first respectively.
    *
    * In some cases, the nature of path fo find may require a specific algorithm. For instance, breadth-first
    * algorithms are necessary to find the shortest path between 2 vertices or when looking for paths in an
    * infinite graph
    *
    */
  def addAdjPaths(remainingPathsToExplore: List[Path], pathsToAdjacentVertices: List[Path]): List[Path]


  /**
    * Condition to be met by the path. The condition will be different for each particular problem, e.g.:
    * - path that passes through an specific vertex (condition used to find a path connecting 2 vertices)
    * - path that passes through all the vertices (condition used to find the solution of problems like the Knight's tour)
    */
  def isSolution(path: Path): Boolean

  /**
    * This method determines if the given vertex is to be added to the path. The implementation will depend on the
    * characteristics of the problem to solve.
    *
    * In most of the cases, it will be necessary to discard a vertex that is already included in the path.
    */
  def isVertexEligibleForPath(vertex: T, path: Path): Boolean

}
