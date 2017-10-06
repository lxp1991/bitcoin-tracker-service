package services

import javax.inject.{Inject, Singleton}

import models.gdax.{DayStats, Product}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GdaxService @Inject()(configuration: Configuration, ws: WSClient)(implicit exec: ExecutionContext) {

  lazy final val endpoint = configuration.underlying.getString("gdax.endpoint")

  def products: Future[Option[Seq[Product]]] = {
    val url = s"$endpoint/products"

    ws.url(url).withMethod("GET").get().map { response =>
      Json.parse(response.body).asOpt[Seq[Product]]
    }
  }

  def stats(productId: String): Future[Option[Seq[DayStats]]] = {
    val url = s"$endpoint/products/$productId/stats"

    ws.url(url).withMethod("GET").get().map { response =>
      Json.parse(response.body).asOpt[Seq[DayStats]]
    }
  }
}
