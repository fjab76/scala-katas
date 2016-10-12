package fjab.factorial

/**
 * Created by franciscoalvarez on 18/09/2016.
 */
object Factorial {

  def tailRecursive(number:Int) : Int = {
    def tailRecursiveAcc (accumulator: Int, number: Int): Int = {
      if (number == 1)
        return accumulator
      tailRecursiveAcc (number * accumulator, number - 1)
    }
    tailRecursiveAcc(1, number)
  }

  def notTailRecursive(number:Int) : Int = {
    if (number == 1)
      return 1
    number * notTailRecursive (number - 1)
  }

}
