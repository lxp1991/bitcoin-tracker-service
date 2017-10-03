package models.gdax

import com.github.tototoshi.play.json.JsonNaming
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
  implicit val productReads = JsonNaming.snakecase(Json.reads[Product])
  implicit val productWrites = Json.writes[Product]
}


