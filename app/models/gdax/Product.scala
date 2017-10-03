package models.gdax

case class Product(id: String,
                   baseCurrency: String,
                   quoteCurrency: String,
                   baseMinSize: BigDecimal,
                   baseMaxSize: BigDecimal,
                   quoteIncrement: BigDecimal,
                   displayName: String,
                   marginEnabled: Boolean)
