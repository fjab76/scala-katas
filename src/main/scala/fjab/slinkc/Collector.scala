package fjab.slinkc

import java.util
import java.util.{Collections, Date}
import java.util.concurrent.{CountDownLatch, TimeUnit}

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.mongodb.scala._
import org.mongodb.scala.bson._

import _root_.scala.collection.mutable.Set
import scala.collection.mutable
import scala.concurrent.{Future, Await}
import _root_.scala.concurrent.duration.Duration
import scala.annotation.tailrec
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by franciscoalvarez on 08/09/2016.
 */
class Collector {

  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("link_collection")
  val collection: MongoCollection[BsonDocument] = database.getCollection("linkDocument");



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
              nextLevelLinks.addAll(childLinks.asJava)//ATTENTION
              countDownLatch.countDown
            }
          }

          f.onFailure{
            case t => {
              println("error: " + t.getMessage)
              countDownLatch.countDown
            }
          }
        })

        println("waiting for current level to complete: " + currentDepth)
        countDownLatch.await
        collect(currentDepth + 1, new util.ArrayList[String](nextLevelLinks).asScala, Collections.synchronizedSet(new java.util.HashSet[String]()))
      }
    }

    collect(0, mutable.Buffer(seed), Collections.synchronizedSet(new java.util.HashSet[String]()))
  }




}
