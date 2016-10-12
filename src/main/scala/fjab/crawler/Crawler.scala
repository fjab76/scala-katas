package fjab.crawler

import java.util.Date
import java.util.concurrent.TimeUnit

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.mongodb.scala._
import org.mongodb.scala.bson._

import _root_.scala.collection.mutable.Set
import _root_.scala.concurrent.Await
import _root_.scala.concurrent.duration.Duration
import scala.annotation.tailrec


/**
 * Created by franciscoalvarez on 08/09/2016.
 */
object Crawler {

  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("link_collection")
  val collection: MongoCollection[BsonDocument] = database.getCollection("linkDocument");


  def main (args: Array[String]){
    collectLinks("http://www.bbc.co.uk", 2)
  }

  def collectLinks(seed: String, depthLimit: Int) = {

    def collectChildLinks(parentLink: String): Set[String] = {
      val childLinks: Set[String] = Set()
      try {
        val doc: Document = Jsoup.connect(parentLink).timeout(500).validateTLSCertificates(false).get
        val elementIterator = doc.select("a[href]").iterator
        while (elementIterator.hasNext) {
          childLinks += elementIterator.next().attr("abs:href")
        }
      }
      catch {
        case e: Exception => println("URL could not be read: " + parentLink + "   =====>  " + e.getMessage)
      }
      childLinks.filter(s => s != null && !s.trim.isEmpty)
    }

    def persist(parentLink: String, childLinks: Set[String], currentDepth: Int) = {

      val doc = BsonDocument("parentLink" -> BsonString(parentLink),
        "childLinks" -> BsonArray(childLinks.toList),
        "seed" -> BsonString(seed),
        "depth" -> BsonInt32(currentDepth),
        "date" -> BsonDateTime(new Date().getTime))

      Await.result(collection.insertOne(doc).toFuture(), Duration(1, TimeUnit.SECONDS))
    }

    @tailrec
    def collect(currentDepth: Int, currentLevelLinks: List[String], nextLevelLinks: Set[String]): Unit = {
      if (currentDepth != depthLimit) {

        val childLinks: Set[String] = collectChildLinks(currentLevelLinks.head)
        persist(currentLevelLinks.head, childLinks, currentDepth)

        if (currentLevelLinks.tail.isEmpty) {
          collect(currentDepth + 1, (nextLevelLinks ++ childLinks).toList, Set())
        }
        else {
          collect(currentDepth, currentLevelLinks.tail, nextLevelLinks ++ childLinks)
        }
      }
    }

    collect(0, List(seed), Set())
  }




}
