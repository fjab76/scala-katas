package fjab.factorial

import scala.annotation.tailrec

/**
 * Created by franciscoalvarez on 26/10/2016.
 */
class Recursion {

  final def tailRecursive(): Unit = tailRecursive()
  def notTailRecursive(): Unit = notTailRecursive()

}
