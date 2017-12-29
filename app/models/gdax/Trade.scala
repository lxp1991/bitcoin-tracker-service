package models.gdax

import java.time.LocalDateTime

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

case class Trade(time: LocalDateTime, trade_id: Long, price: Double, size: Double, side: String)

object Trade {
  implicit val tradeRead: Reads[Trade] = (
    (JsPath \ "time").read[LocalDateTime] and
      (JsPath \ "trade_id").read[Long] and
      (JsPath \ "price").read[Double] and
      (JsPath \ "size").read[Double] and
      (JsPath \ "side").read[String]
    ) (Trade.apply _)
}
