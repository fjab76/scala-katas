package fjab.challenge

/**
  * Created by franciscoalvarez on 11/06/2017.
  *
  * Knight's tour problem
  * Given a finite chess board and a knight, calculate the list of moves to touch one and only one time
  * on each square of the board
  */
class KnightsTour(x: Int, y: Int) extends IndependentKnowledgeGraph[Coordinate]{

  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))


  override def adj(coordinate: Coordinate): List[Coordinate] = {
    val list = new scala.collection.mutable.ListBuffer[Coordinate]()
    moves.foreach{ tuple =>
      val (v, w) = (coordinate._1 + tuple._1,coordinate._2 + tuple._2)
      if(v >= 0 && v < x && w >=0 && w<y)
        list += ((v,w))
    }
    list.toList
  }


  override def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point] =
    adjacentPoints ++ list //depth-first search


 }
