package fjab.challenge

/**
 * Created by franciscoalvarez on 19/05/2017.
 */
object Queens {

  type Coordinate = (Int, Int)
  def queens(n: Int): List[List[Coordinate]] = {

    def isSafe(queen: Coordinate, queens: List[Coordinate]): Boolean =
      queens forall(q => !inCheck(queen, q))

    def inCheck(q1: Coordinate, q2: Coordinate): Boolean =
      q1._1 == q2._1 || q1._2 == q2._2 || (q1._1 - q2._1).abs == (q1._2 - q2._2).abs

    def placeQueens(k: Int): List[List[Coordinate]] = {
      if(k == 0)
        List(List())
      else
        for{
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens
    }

    placeQueens(n)
  }
}
