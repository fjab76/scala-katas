package fjab.challenge

import fjab.challenge.old.NegativeBinaryOg.moves

/**
 * Created by franciscoalvarez on 04/06/2017.
 *
 * Given an integer, calculate the shortest sequence of bits representing that number in base -2
 * In base -2, the sequence B of N bits represents the number
 * sum{B[i]*(-2).pow(i) for i=0...N-1}
 * The empty sequence represents 0
 *
 */
object NegativeBinary extends HistoryAwareGraph[Value]{

  val moves: List[Value] = List(0, 1)

  def findBinaryRepresentation(value: Value) = super.findPath(List((0, List(0)), (1, List(1))), value)

  override def adj(point: Point): List[Point] = {
    val value = point._1
    val binaryRepresentation = point._2
    List((value, moves(0) :: binaryRepresentation), (value + BigInt(-2).pow(binaryRepresentation.size).toInt, moves(1) :: binaryRepresentation))
  }

  override def addAdjPoints(list: List[Point], adjacentPoints: List[Point]): List[Point] =
    list ++ adjacentPoints //breadth-first search
}
