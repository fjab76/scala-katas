package fjab.challenge

/**
 * Created by franciscoalvarez on 30/10/2016.
 */
object OptimalChange {

  type CoinDenomination = Int

  def greedyOptimalChange(coins: List[CoinDenomination], amount: Int): List[Int] = {
    if(amount == 0) Nil
    else {
      val greatestCandidateCoin = coins.filter(_ <= amount).max
      greedyOptimalChange(coins, amount - greatestCandidateCoin) ::: List(greatestCandidateCoin)
    }
  }

  def optimalChange(coins: List[CoinDenomination], amount: Int): List[Int] = amount match {
    case 0 => Nil
    case x => coins.filter(_ <= x).map(coin => coin :: optimalChange(coins, x - coin)).minBy(_.length)
  }

  def optimalNumberCoins(coins: List[CoinDenomination], amount: Int): Int = amount match {
    case 0 => 0
    case x => coins.filter (_ <= x).map (coin => 1 + optimalNumberCoins (coins, x - coin) ).min
  }

}
