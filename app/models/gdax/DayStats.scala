package models.gdax

import play.api.libs.json.Json


case class DayStats(id: String,
                    timestamp: Long,
                    open: BigDecimal,
                    high: BigDecimal,
                    low: BigDecimal,
                    volume: BigDecimal,
                    last: BigDecimal,
                    volume30day: BigDecimal)

object DayStats {
  implicit val dayStatsReads = Json.format[DayStats]
  implicit val dayStatsWrites = Json.writes[DayStats]
}
