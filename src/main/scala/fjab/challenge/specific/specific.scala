package fjab.challenge

/**
  * Created by franciscoalvarez on 29/07/2017.
  */
package object specific {

  type Coordinate = (Int, Int)

  implicit class RichTuple2(coordinate: Coordinate){
    def +(other: Coordinate) = (coordinate._1 + other._1, coordinate._2 + other._2)
    def -(other: Coordinate) = (coordinate._1 - other._1, coordinate._2 - other._2)
  }
}