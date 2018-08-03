package models.coinbase

import play.api.libs.json.Json

object Coinbase {

  case class Ticker(trade_id: Long, price: String, size: String, bid: String, ask: String, volume: String, time: String)

  implicit val ticker = Json.format[Ticker]


}
