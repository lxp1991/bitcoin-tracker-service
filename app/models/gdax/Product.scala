package models.gdax

import play.api.libs.json.Json

case class Product(id: String,
                   baseCurrency: String,
                   quoteCurrency: String,
                   baseMinSize: BigDecimal,
                   baseMaxSize: BigDecimal,
                   quoteIncrement: BigDecimal,
                   displayName: String,
                   marginEnabled: Boolean)

object Product {
  implicit val productReads = Json.format[Product]
}


