package services

import akka.actor.ActorSystem
import daos.DatastoreDao
import javax.inject.{Inject, Singleton}
import models.coinbase.Coinbase.Ticker
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HuobiService @Inject()(configuration: Configuration,
                             ws: WSClient,
                             datastoreDao: DatastoreDao,
                             actorSystem: ActorSystem)(implicit exec: ExecutionContext) {

  lazy final val endpoint = configuration.underlying.getString("coinbase.endpoint")
  val logger = Logger(this.getClass)

  def trades: Future[Option[List[Ticker]]] = {
    //    val url = s"$endpoint/market/trade"
    //    ws.url(url)
    //      .withMethod("GET")
    //      .addQueryStringParameters("symbol" -> "btcusdt")
    //      .get.map { response =>
    //      response.json.asOpt[List[Trade]]
    //    }
    Future(None)
  }


}
