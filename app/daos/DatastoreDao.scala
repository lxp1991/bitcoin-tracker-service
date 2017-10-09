package daos

import javax.inject.Inject

import com.google.cloud.datastore._
import models.gdax.{DayStats, Product}

import scala.collection.mutable.ListBuffer

class DatastoreDao @Inject()() {

  final val ProjectId = "lxp1991"
  final val Namespace = "dev"

  final val ProductKind = "Product"
  final val DayStatsKind = "DayStats"
  lazy val Options = DatastoreOptions
    .newBuilder()
    .setNamespace(Namespace)
    .setProjectId(ProjectId)
    .build()
  lazy val Datastore = Options.getService

  lazy val KeyFactory = Datastore.newKeyFactory()

  def createProduct(product: Product) = {
    val key = KeyFactory.setKind(ProductKind).newKey(product.id)
    val entity = Entity.newBuilder(key)
      .set("baseCurrency", product.baseCurrency)
      .set("quoteCurrency", product.quoteCurrency)
      .set("baseMinSize", product.baseMinSize.toString)
      .set("baseMaxSize", product.baseMaxSize.toString)
      .set("quoteIncrement", product.quoteIncrement.toString)
      .set("displayName", product.displayName)
      .set("marginEnabled", product.marginEnabled)
      .build()
    Datastore.put(entity)
    key
  }

  def createDayStats(dayStats: DayStats) = {
    val key = KeyFactory.setKind(DayStatsKind).newKey(dayStats.timestamp)
    val entity = Entity.newBuilder(key)
      .set("productId", dayStats.id)
      .set("open", dayStats.open.toString)
      .set("high", dayStats.high.toString)
      .set("low", dayStats.low.toString)
      .set("volume", dayStats.volume.toString)
      .set("last", dayStats.last.toString)
      .set("volume30day", dayStats.volume30day.toString)
      .build()
    Datastore.put(entity)
    key
  }


  /**
    * return all the product ids from gdax
    *
    * @return
    */
  def productIds: List[String] = {
    val query: Query[Key] = Query.newKeyQueryBuilder.setKind(ProductKind).build
    val readOption = ReadOption.eventualConsistency()
    val results: QueryResults[Key] = Datastore.run(query, readOption)

    var res = new ListBuffer[String]()
    while (results.hasNext) {
      val key = results.next()
      res += key.getName
    }
    res.toList
  }

}