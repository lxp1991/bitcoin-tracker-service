package services

import akka.actor.ActorSystem
import daos.DatastoreDao
import javax.inject.{Inject, Singleton}
import models.coinbase.Coinbase.Ticker
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CoinbaseService @Inject()(configuration: Configuration,
                                ws: WSClient,
                                datastoreDao: DatastoreDao,
                                actorSystem: ActorSystem)(implicit exec: ExecutionContext) {

  lazy final val endpoint = configuration.underlying.getString("coinbase.endpoint")
  val logger = Logger(this.getClass)

  def run = {
    trades
  }

  def trades: Future[Option[List[Ticker]]] = {
    //    val url = s"$endpoint/products/BTC-USD/trades"
    //    ws.url(url).withMethod("GET").get.map { response =>
    //      response.json.asOpt[List[Trade]](Trade.fromGdax)
    //    }
    Future(None)
  }

  def products = {
    //    val url = s"$endpoint/products"
    //
    //    ws.url(url).withMethod("GET").get().map { response =>
    //      val result = response.json.asOpt[Seq[Product]]
    //      result.map { r => r.map { s => datastoreDao.createProduct(s) } }
    //      result
    //    }
  }

  def ticket(productId: String) = {
    val url = s"$endpoint/products/$productId/ticker"
    ws.url(url)
      .withMethod("GET")
      .get()
      .map {
        _.json.asOpt[Ticker]
      }

  }

  def runStats(productId: String) = {
    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 10.seconds) {
      stats(productId)
    }
  }

  def stats(productId: String) = {
    //    val url = s"$endpoint/products/$productId/stats"
    //
    //    ws.url(url).withMethod("GET").get().map { response =>
    //      val json = response.json
    //      val currentTimeEpoch = Instant.now.toEpochMilli
    //      val dayStats = DayStats(productId,
    //        currentTimeEpoch,
    //        (json \ "open").as[BigDecimal],
    //        (json \ "high").as[BigDecimal],
    //        (json \ "low").as[BigDecimal],
    //        (json \ "volume").as[BigDecimal],
    //        (json \ "last").as[BigDecimal],
    //        (json \ "volume_30day").as[BigDecimal])
    //      datastoreDao.createDayStats(dayStats)
    //      dayStats
    //    }
  }

  def productIds = {
    //    datastoreDao.productIds
  }

}
