package controllers.gdax

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}
import services.GdaxService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MarketDataController @Inject()(cc: ControllerComponents,
                                     configuration: Configuration,
                                     gdaxService: GdaxService)(implicit exec: ExecutionContext) extends AbstractController(cc) {


  def products = Action.async {
    Future(Ok("No"))
  }

  def stats = Action.async {
    //    gdaxService.stats("BTC-USD").map { result => Ok(Json.toJson(result)) }
    Future(Ok("No"))
  }

  def runStats = Action.async {
    gdaxService.runStats("BTC-USD")
    Future(Ok("Job has been started"))
  }

  def run = Action.async {
    gdaxService.run.map {
      Ok(_)
    }
    //    Future(Ok("triggered"))
  }
}
