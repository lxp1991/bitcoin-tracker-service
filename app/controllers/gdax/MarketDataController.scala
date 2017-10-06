package controllers.gdax

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.GdaxService

import scala.concurrent.ExecutionContext

@Singleton
class MarketDataController @Inject()(cc: ControllerComponents,
                                     configuration: Configuration,
                                     gdaxService: GdaxService)(implicit exec: ExecutionContext) extends AbstractController(cc) {


  def products = Action.async {
    gdaxService.products.map { result => Ok(Json.toJson(result)) }
  }

  def stats = Action.async {
    gdaxService.stats("BTC-USD").map { result => Ok(Json.toJson(result)) }
  }
}
