package fjab

/**
 * Created by franciscoalvarez on 24/06/2017.
 */
package object challenge {

  type Coordinate = (Int, Int)

  implicit class RichTuple2(tuple: Tuple2[Int,Int]){
    def +(other: (Int, Int)) = (tuple._1 + other._1, tuple._2 + other._2)
    def -(other: (Int, Int)) = (tuple._1 - other._1, tuple._2 - other._2)
  }
}
