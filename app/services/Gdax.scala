package services

import javax.inject.{Inject, Singleton}

import models.gdax.Product
import play.api.Configuration
import play.api.libs.ws.WSClient

import scala.concurrent.Future

trait Gdax {

  def products(): Future[Seq[Product]]
}

@Singleton
class Gdax @Inject()(configuration: Configuration, ws: WSClient) extends Gdax {

  lazy final val endpoint = configuration.underlying.getString("gdax.endpoint")

  override def products(): Future[Seq[Product]] = {
    val url = s"$endpoint/products"
    ws.url(url).withMethod("GET").get().map { response =>
      response.json.validate[Seq[Product]]
    }
  }
}
