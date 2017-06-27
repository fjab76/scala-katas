package fjab.graphs

/**
 * Created by franciscoalvarez on 22/06/2017.
 */

class Graph(edges: List[(Vertex, Vertex)]) {

  val numVertices = edges.flatMap(edge => List(edge._1, edge._2)).distinct.length
  val numEdges = edges.length
  private val internalRepresentation = new Array[scala.collection.mutable.HashSet[Vertex]](numVertices)

  for(i <- 0 until numVertices){
    internalRepresentation(i) = new scala.collection.mutable.HashSet[Vertex]
  }

  edges.foreach{ edge =>
    internalRepresentation(edge._1) += edge._2
    internalRepresentation(edge._2) += edge._1
  }


  def adj(v: Vertex): Iterable[Vertex] = internalRepresentation(v).toList //returning an immutable representation

  def degree(v: Int): Int = adj(v).size
  def maxDegree(): Int = (0 until numVertices).map(degree(_)).max
  def avgDegree(): Int = 2 * numEdges / numVertices
  def numberOfSelfLoops(): Int =
    (for {
      i <- 0 until numVertices
      j <- adj(i)
      if(i == j)
    } yield i
      ).size

  override def toString(): String = {
    val sb = new StringBuilder
    internalRepresentation.zipWithIndex.foreach{ tuple =>
      sb.append(tuple._2).append(" => ")
      tuple._1.addString(sb, "",",", "\n")
    }
    sb.toString()
  }

}


