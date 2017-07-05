package fjab.challenge.old

/**
 * Created by franciscoalvarez on 11/06/2017.
 *
 * Given an infinite chess board and a knight, calculate the shortest list of moves to reach a given position on the board
 */
object ChessKnight {

  type Coordinate = (Int, Int)
  type Point = (Coordinate, List[Coordinate])
  val moves: List[Coordinate] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))
  val startingPoints = List(((0,0), Nil))
  def done(currentValue: Coordinate, finalValue: Coordinate) =
    currentValue._1 == finalValue._1 && currentValue._2 == finalValue._2

  def neighbours(currentPoint: Point): List[Point] = {
    val list = new scala.collection.mutable.ListBuffer[Point]()
    val value = currentPoint._1
    val movesHistory = currentPoint._2
    moves.foreach{ tuple =>
      val newPoint: Point = ((value._1 + tuple._1, value._2 + tuple._2), tuple :: movesHistory)
      list += newPoint
    }
    list.toList
  }

  def newNeighbours(currentPoint: Point, explored: Set[Coordinate]) =
    neighbours(currentPoint) filter{p => !explored.contains(p._1)}

  def recursiveSolution(goal: Coordinate): List[Coordinate] = {

    def from(initial: Stream[Point], explored: Set[Coordinate]): Stream[Point] = initial match{
      case Stream.Empty => Stream.empty
      case (value, movesHistory) #:: rest => {
        val more = newNeighbours((value, movesHistory), explored).toStream
        (value, movesHistory) #:: from(rest ++ more, explored + value)
        //given that the chess board is infinite, breadth-first approach is necessary
      }
    }

    lazy val pathsFromStart: Stream[Point] = from(startingPoints.toStream, Set((0,0)))
    lazy val pathsToGoal: Stream[Point] = pathsFromStart filter {p => done(p._1, goal)}

    pathsToGoal.take(1)(0)._2.reverse
//    pathsToGoal match{
//      case Stream.Empty => Nil
//      case (v, r) #:: _ => r.reverse
//    }
  }


  def tailRecursiveSolution(target: Coordinate): List[Coordinate] = {

    def fromAccum(initial: List[Point], explored: Set[Coordinate]): List[Point] = initial match{
      case Nil => Nil
      case (value, movesHistory) :: rest =>
        if(value == target) initial
        else {
          val more = newNeighbours((value, movesHistory), explored)
          fromAccum(rest ++ more, explored + value)
        }

    }

    lazy val pathsFromStart: List[Point] = fromAccum(startingPoints, Set())
    //lazy val pathsToGoal: List[(Int, List[Int])] = pathsFromStart filter {_._1 == v}

    pathsFromStart match{
      case Nil => Nil
      case (v, r) :: _ => r.reverse
    }
  }

}
