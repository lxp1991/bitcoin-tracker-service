package utils

object Utilities {

  def fromOption(value: Option[Any]) = value match {
    case Some(v) => v
    case None => "Error"
  }
}
