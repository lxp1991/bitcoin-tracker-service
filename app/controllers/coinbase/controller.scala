package controllers.coinbase

import javax.inject.{Inject, Singleton}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.CoinbaseService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class controller @Inject()(cc: ControllerComponents,
                           configuration: Configuration,
                           coinbaseService: CoinbaseService)(implicit exec: ExecutionContext) extends AbstractController(cc) {


  def products = Action.async {
    Future(Ok("No"))
  }

  def stats = Action.async {
    Future(Ok("No"))
  }

  def ticker = Action.async {
    coinbaseService.ticket("BTC-USD").map { r =>
      Ok(Json.toJson(r))
    }
  }

  def runStats = Action.async {
    coinbaseService.runStats("BTC-USD")
    Future(Ok("Job has been started"))
  }

  def run = Action.async {
    //    coinbaseService.run.map {
    //      Ok("okd")
    //    }
    Future {
      Ok
    }
  }
}
