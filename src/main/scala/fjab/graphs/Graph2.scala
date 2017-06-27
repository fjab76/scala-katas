package fjab.graphs

/**
 * Created by franciscoalvarez on 24/06/2017.
 */
class Graph2(edges: List[(Vertex, Vertex)]) {

  val numVertices = edges.flatMap(edge => List(edge._1, edge._2)).distinct.length
  val numEdges = edges.length
  private val temporaryRepresentation = new Array[scala.collection.mutable.HashSet[Vertex]](numVertices)
  private val graphRepresentation = new Array[List[Vertex]](numVertices)

  for(i <- 0 until numVertices){
    temporaryRepresentation(i) = new scala.collection.mutable.HashSet[Vertex]
  }

  edges.foreach{ edge =>
    temporaryRepresentation(edge._1) += edge._2
    temporaryRepresentation(edge._2) += edge._1
  }

  for(i <- 0 until numVertices){
    graphRepresentation(i) = temporaryRepresentation(i).toList
  }

  def existPath(from: Vertex, to: Vertex): Boolean = {

    def isSolution(vertex: Vertex) = vertex == to

    def solution(verticesAhead: List[Vertex], explored: Set[Vertex]): List[Vertex] = verticesAhead match{
      case Nil => Nil
      case currentVertex :: rest =>
        if(isSolution(currentVertex)) verticesAhead
        else{
          val adjacentVertices = adj(currentVertex).filterNot(explored.contains(_))
          solution(adjacentVertices ++ rest, explored + currentVertex) //depth-first search
        }
    }

    solution(List(from), Set(from)).length > 0
  }

  def findPath(from: Vertex, to: Vertex): Path = {

    def isSolution(vertex: Vertex) = vertex == to

    def solution(verticesAhead: List[Point], explored: Set[Vertex]): Path = verticesAhead match{
      case Nil => Nil
      case (currentVertex, pathToCurrentVertex) :: rest =>
        if(isSolution(currentVertex)) pathToCurrentVertex
        else{
          val adjacentVertices = adj(currentVertex).filterNot(explored.contains(_))
          val adjacentPoints = adjacentVertices.map(vertex => (vertex, vertex :: pathToCurrentVertex))
          solution(adjacentPoints ++ rest, explored + currentVertex) //depth-first search
        }
    }

    solution(List((from, List())), Set(from)) match{
      case Nil => Nil
      case path => path.reverse
    }
  }


  def adj(v: Vertex): List[Vertex] = graphRepresentation(v)

  def degree(v: Int): Int = adj(v).size
  def maxDegree(): Int = (0 until numVertices).map(degree(_)).max
  def avgDegree(): Int = 2 * numEdges / numVertices
  def numberOfSelfLoops(): Int = (for {
      i <- 0 until numVertices
      j <- adj(i)
      if(i == j)
    } yield i
      ).length

  override def toString(): String = {
    val sb = new StringBuilder
    temporaryRepresentation.zipWithIndex.foreach{ tuple =>
      sb.append(tuple._2).append(" => ")
      tuple._1.addString(sb, "",",", "\n")
    }
    sb.toString()
  }

}
