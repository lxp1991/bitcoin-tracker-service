package controllers.gdax

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class MarketDataController @Inject()(cc: ControllerComponents, configuration: Configuration)(implicit exec: ExecutionContext) extends AbstractController(cc) {


  def products = Action.async {

  }
}
