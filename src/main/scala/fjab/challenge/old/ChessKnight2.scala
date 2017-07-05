package fjab.challenge.old

/**
  * Created by franciscoalvarez on 11/06/2017.
  *
 * Knight's tour problem
  * Given a finite chess board and a knight, calculate the shortest list of moves to touch one and only one time
  * on each square of the board
  */
object ChessKnight2 {

  type Coordinate = (Int, Int)
  type Move = (Int, Int)
  type Point = (Coordinate, List[Move], Set[Coordinate])//Current coordinate, List of moves, Set of visited coordinates
  type Terrain = Coordinate => Boolean

  val moves: List[Move] = List((2,1), (1,2), (-1,2), (-2,1), (-2,-1), (-1,-2), (1,-2), (2,-1))

  def done(numberExploredCoordinates: Int)(implicit terrainDimensions: (Int, Int)) = numberExploredCoordinates == terrainDimensions._1 * terrainDimensions._2

  def newNeighbours(currentPoint: Point)(implicit terrainDimensions: (Int, Int)): List[Point] = {

    def isLegalMove(coordinate: Coordinate)(implicit terrainDimensions: (Int, Int)) = terrain(coordinate)
    def isNotRepeatMove(newCoordinate: Coordinate, exploredCoordinates: Set[Coordinate]): Boolean = !exploredCoordinates.contains(newCoordinate)
    def terrain(coordinate: Coordinate)(implicit terrainDimensions: (Int, Int)) = coordinate match {
      case (x: Int, y: Int) if (x >= 0 && x<terrainDimensions._1 && y >= 0 && y<terrainDimensions._2) => true
      case other => false
    }

    val list = new scala.collection.mutable.ListBuffer[Point]()
   val currentCoordinate = currentPoint._1
   val movesHistory = currentPoint._2
    val exploredCoordinates = currentPoint._3
   moves.foreach{ move =>
     val newCoordinate = (currentCoordinate._1 + move._1, currentCoordinate._2 + move._2)
     if(isLegalMove(newCoordinate) && isNotRepeatMove(newCoordinate, exploredCoordinates)){
       val newPoint: Point = (newCoordinate, move :: movesHistory, exploredCoordinates + newCoordinate)
       list += newPoint
     }
   }
   list.toList
  }


  def recursiveSolution(initialPosition: Coordinate)(implicit terrainDimensions: (Int, Int)): List[Move] = {

   def from(initial: Stream[Point]): Stream[Point] = initial match{
     case Stream.Empty => Stream.empty
     case (value, movesHistory, exploredCoordinates) #:: rest => {
       val more = newNeighbours(value, movesHistory, exploredCoordinates).toStream
       (value, movesHistory, exploredCoordinates) #:: from(more ++ rest)
     }
   }

    val startingPoints: List[Point] = List((initialPosition, List[Move](), Set(initialPosition)))
   lazy val pathsFromStart: Stream[Point] = from(startingPoints.toStream)
   lazy val pathsToGoal: Stream[Point] = pathsFromStart filter {p => done(p._3.size)}

   //pathsToGoal.take(1)(0)._2.reverse
    //pathsToGoal
      pathsToGoal match{
        case Stream.Empty => Nil
        case (v, r, e) #:: _ => r.reverse
      }
  }


  def tailRecursiveSolution(initialPosition: Coordinate)(implicit terrainDimensions: (Int, Int)): List[Move] = {

   def fromAccum(initial: List[Point]): List[Point] = initial match{
     case Nil => Nil
     case (currentPosition, movesHistory, exploredCoordinates) :: rest =>
       if(done(exploredCoordinates.size)) initial
       else {
         val more = newNeighbours((currentPosition, movesHistory, exploredCoordinates))
         //fromAccum(rest ++ more) //breadth-first is super slow as it requires examining all combinations before finding the solution
         fromAccum(more ++ rest) //depth-first is super fast as it examines combinations one at a time
         //for finite spaces of solutions, depth-first makes more sense
       }
   }

    val startingPoints: List[Point] = List((initialPosition, List[Move](), Set(initialPosition)))
   lazy val pathsFromStart: List[Point] = fromAccum(startingPoints)

   pathsFromStart match{
     case Nil => Nil
     case (v, r, e) :: _ => r.reverse
   }
  }



 }
