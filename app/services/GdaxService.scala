package services

import java.time.Instant
import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import daos.DatastoreDao
import models.gdax.{DayStats, Product}
import play.api.Configuration
import play.api.libs.ws.WSClient

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GdaxService @Inject()(configuration: Configuration,
                            ws: WSClient,
                            datastoreDao: DatastoreDao,
                            actorSystem: ActorSystem)(implicit exec: ExecutionContext) {

  lazy final val endpoint = configuration.underlying.getString("gdax.endpoint")

  def products: Future[Option[Seq[Product]]] = {
    val url = s"$endpoint/products"

    ws.url(url).withMethod("GET").get().map { response =>
      val result = response.json.asOpt[Seq[Product]]
      result.map { r => r.map { s => datastoreDao.createProduct(s) } }
      result
    }
  }

  def stats(productId: String) = {
    val url = s"$endpoint/products/$productId/stats"

    ws.url(url).withMethod("GET").get().map { response =>
      val json = response.json
      val currentTimeEpoch = Instant.now.toEpochMilli
      val dayStats = DayStats(productId,
        currentTimeEpoch,
        (json \ "open").as[BigDecimal],
        (json \ "high").as[BigDecimal],
        (json \ "low").as[BigDecimal],
        (json \ "volume").as[BigDecimal],
        (json \ "last").as[BigDecimal],
        (json \ "volume_30day").as[BigDecimal])
      datastoreDao.createDayStats(dayStats)
      dayStats
    }
  }

  def runStats(productId: String) = {
    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 10.seconds) {
      stats(productId)
    }
  }

  def productIds: Seq[String] = {
    datastoreDao.productIds
  }

}
