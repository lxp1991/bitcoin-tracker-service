package daos

import java.io.FileInputStream
import java.time.Instant

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.datastore._
import models.gdax.{DayStats, Product}

class DatastoreDao {

  final val ProjectId = "lxp1991"
  final val Namespace = "dev"

  final val ProductKind = "Product"
  final val DayStatsKind = "DayStats"
  lazy val Options = DatastoreOptions
    .newBuilder()
    .setNamespace(Namespace)
    .setProjectId(ProjectId)
    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\lxp19\\lxp1991-efcd74acaf4c.json")))
    .build()
  lazy val Datastore = Options.getService

  lazy val KeyFactory = Datastore.newKeyFactory()

  def createProduct(product: Product): Key = {
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

  def createDayStats(dayStats: DayStats): Key = {
    val key = KeyFactory.setKind(DayStatsKind).newKey(Instant.now.toEpochMilli)
    val entity = Entity.newBuilder(key)
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

  //
  //  def productIds = {
  //    val query: Query[Entity] = Query.newEntityQueryBuilder()
  //      .setKind(ProductKind)
  //      .build()
  //    val results: QueryResults[Entity] = Datastore.run(query)
  //    val query: Query[Key] = Query.newKeyQueryBuilder()
  //      .setKind(ProductKind)
  //      .build()
  //
  //    val result: QueryResults[Key] = Datastore.run(query)
//}

}