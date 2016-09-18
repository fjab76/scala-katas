package fjab.slinkc

import java.util.concurrent.TimeUnit

import org.mongodb.scala._
import org.mongodb.scala.bson.BsonString

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by franciscoalvarez on 08/09/2016.
 */
object App {

  def main(args: Array[String]) {

    new Collector().collectLinks("http://www.bbc.co.uk", 10)
  }

}
