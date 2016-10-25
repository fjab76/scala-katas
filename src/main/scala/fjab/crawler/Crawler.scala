package fjab.crawler

import java.util
import java.util.concurrent.{CountDownLatch, TimeUnit}
import java.util.{Collections, Date}

import com.typesafe.scalalogging.Logger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.mongodb.scala._
import org.mongodb.scala.bson._
import org.slf4j.LoggerFactory

import _root_.scala.collection.mutable.Set
import _root_.scala.concurrent.duration.Duration
import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}


/**
 * Created by franciscoalvarez on 08/09/2016.
 */
object Crawler {

  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("link_collection")
  val collection: MongoCollection[BsonDocument] = database.getCollection("linkDocument")
  val logger = Logger(LoggerFactory.getLogger("Collector"))


  def main (args: Array[String]){
    collectLinks("http://www.bbc.co.uk", 3)
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
        case e: Exception => //println("URL could not be read: " + parentLink + "   =====>  " + e.getMessage)
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
    def collect(currentDepth: Int, currentLevelLinks: mutable.Buffer[String], nextLevelLinks: java.util.Set[String]): Unit = {
      if (currentDepth != depthLimit) {

        val countDownLatch: CountDownLatch = new CountDownLatch(currentLevelLinks.size)
        currentLevelLinks.foreach(link => {
          val f: Future[Set[String]] = Future {
            collectChildLinks(link)
          }

          f.onSuccess{
            case childLinks => {
              Future {persist(link, childLinks, currentDepth)}
              Future{
                nextLevelLinks.addAll(childLinks.asJava)
                countDownLatch.countDown
              }
            }
          }

          f.onFailure{
            case t => {
              logger.error(s"error: $t.getMessage")
              countDownLatch.countDown
            }
          }
        })

        logger.info(s"waiting for current level to complete: $currentDepth")
        countDownLatch.await
        collect(currentDepth + 1, new util.ArrayList[String](nextLevelLinks).asScala, Collections.synchronizedSet(new java.util.HashSet[String]()))
      }
    }

    collect(0, mutable.Buffer(seed), Collections.synchronizedSet(new java.util.HashSet[String]()))
  }




}
