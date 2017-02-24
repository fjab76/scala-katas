package fjab

/**
 * Created by franciscoalvarez on 30/10/2016.
 */
object OptimalChange {

  def greedyOptimalChange(coins: List[Int], amount: Int): List[Int] = {
    if(amount == 0) Nil
    else {
      val greatestCandidateCoin = coins.filter(coin => coin <= amount).max
      greedyOptimalChange(coins, amount - greatestCandidateCoin) ::: List(greatestCandidateCoin)
    }
  }

  def optimalChange(coins: List[Int], amount: Int): List[Int] = amount match {
    case 0 => Nil
    case x if x > 0 => coins.filter (coin => coin <= x).map (coin => coin :: optimalChange (coins, x - coin) ).minBy (_.length)
  }

  def optimalNumberCoins(coins: List[Int], amount: Int): Int = amount match {
    case 0 => 0
    case x if x > 0 => coins.filter (coin => coin <= x).map (coin => 1 + optimalNumberCoins (coins, x - coin) ).min
  }

}
