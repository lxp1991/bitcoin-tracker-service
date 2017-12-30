package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Trade(time: String, price: BigDecimal, size: BigDecimal, side: String)

object Trade {

  implicit val fromGdax: Reads[Trade] = (
    (JsPath \ "time").read[String] and
      (JsPath \ "price").read[BigDecimal] and
      (JsPath \ "size").read[BigDecimal] and
      (JsPath \ "side").read[String]
    ) (Trade.apply _)

  implicit val fromHuobi: Reads[Trade] = (
    (JsPath \ "tick" \ "data" \ 0 \ "ts").read[String] and
      (JsPath \ "tick" \ "data" \ 0 \ "price").read[BigDecimal] and
      (JsPath \ "tick" \ "data" \ 0 \ "amount").read[BigDecimal] and
      (JsPath \ "tick" \ "data" \ 0 \ "side").read[String]
    ) (Trade.apply _)
}
