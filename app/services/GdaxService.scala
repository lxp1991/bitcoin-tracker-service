package services

import javax.inject.{Inject, Singleton}

import daos.DatastoreDao
import models.gdax.{DayStats, Product}
import play.api.Configuration
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GdaxService @Inject()(configuration: Configuration, ws: WSClient, datastoreDao: DatastoreDao)(implicit exec: ExecutionContext) {

  lazy final val endpoint = configuration.underlying.getString("gdax.endpoint")

  def products: Future[Option[Seq[Product]]] = {
    val url = s"$endpoint/products"

    ws.url(url).withMethod("GET").get().map { response =>
      val result = response.json.asOpt[Seq[Product]]
      result.map { r => r.map { s => datastoreDao.createProduct(s) } }
      datastoreDao.createProduct(result.get(0))
      result
    }
  }

  def stats(productId: String): Future[Option[DayStats]] = {
    val url = s"$endpoint/products/$productId/stats"

    ws.url(url).withMethod("GET").get().map { response =>
      val result = response.json.asOpt[DayStats]
      result.map(r => datastoreDao.createDayStats(r))
      result
    }
  }
}
