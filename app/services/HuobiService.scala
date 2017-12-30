package services

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import daos.DatastoreDao
import models.gdax.Trade
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HuobiService @Inject()(configuration: Configuration,
                             ws: WSClient,
                             datastoreDao: DatastoreDao,
                             actorSystem: ActorSystem)(implicit exec: ExecutionContext) {

  val logger = Logger(this.getClass)
  lazy final val endpoint = configuration.underlying.getString("gdax.endpoint")


  def trades: Future[Option[List[Trade]]] = {
    val url = s"$endpoint/market/trade"
    ws.url(url)
      .withMethod("GET")
      .addQueryStringParameters("symbol" -> "btcusdt")
      .get.map { response =>
      response.json.asOpt[List[Trade]]
    }
  }


}
