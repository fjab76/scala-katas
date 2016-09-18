package fjab.slinkc

/**
 * Created by franciscoalvarez on 18/09/2016.
 */
class Factorial {

  def tailRecursive(accumulator: Int, number: Int) : Int = {
    if(number == 1)
      return accumulator
    tailRecursive(number * accumulator, number - 1)
  }

  def notTailRecursive(number:Int) : Int = {
    if (number == 1)
      return 1
    number * notTailRecursive (number - 1)
  }

}
