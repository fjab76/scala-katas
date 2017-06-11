package fjab.challenge

/**
 * Created by franciscoalvarez on 04/06/2017.
 *
 * Given an integer, calculate the shortest sequence of bits representing that number in base -2
 * In base -2, the sequence B of N bits represents the number
 * sum{B[i]*(-2).pow(i) for i=0...N-1}
 * The empty sequence represents 0
 *
 */
object NegativeBinary {

  type Coordinate = Int
  type Point = (Coordinate, List[Coordinate]) // (value, binaryRepresentation)
  val moves: List[Coordinate] = List(0, 1)
  val startingPoints = List((0, List(0)), (1, List(1)))
  def done(currentValue: Coordinate, finalValue: Coordinate) = currentValue == finalValue

  def neighbours(currentPoint: Point): List[Point] = {
    val value = currentPoint._1
    val binaryRepresentation = currentPoint._2
    List((value, moves(0) :: binaryRepresentation), (value + BigInt(-2).pow(binaryRepresentation.size).toInt, moves(1) :: binaryRepresentation))
  }

  def newNeighbours(currentPoint: Point) =
    neighbours(currentPoint)

  def recursiveSolution(goal: Int): List[Int] = {

    def from(initial: Stream[Point]): Stream[Point] = initial match{
      case Stream.Empty => Stream.empty
      case (value, binaryRepresentation) #:: rest => {
        val more = newNeighbours(value, binaryRepresentation).toStream
        (value, binaryRepresentation) #:: from(rest ++ more)
        //for this specific problem, there are never repeat positions
        //here we are stretching the concept of history of moves to accommodate the binary representation
      }
    }

    lazy val pathsFromStart: Stream[Point] = from(startingPoints.toStream)
    lazy val pathsToGoal: Stream[Point] = pathsFromStart filter { p => done(p._1, goal)}

    pathsToGoal match{
      case Stream.Empty => Nil
      case (v, r) #:: _ => if(v == 0) Nil else r.reverse
    }
  }

  def tailRecursiveSolution(finalValue: Int): List[Int] = {

    def fromAccum(initial: List[Point]): List[Point] = initial match{
      case Nil => Nil
      case (value, binaryRepresentation) :: rest =>
        if(value == finalValue) initial
        else {
          val more = newNeighbours((value, binaryRepresentation))
          fromAccum(rest ++ more)
          //for this specific problem, there are never repeat positions
          //here we are stretching the concept of history of moves to accommodate the binary representation
        }

    }

    lazy val pathsFromStart: List[Point] = fromAccum(startingPoints)
    //lazy val pathsToGoal: List[(Int, List[Int])] = pathsFromStart filter {_._1 == v}

    pathsFromStart match{
      case Nil => Nil
      case (v, r) :: _ => if(v == 0) Nil else r.reverse
    }
  }



}
