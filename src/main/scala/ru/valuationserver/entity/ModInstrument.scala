package ru.valuationserver.entity

import spray.json.DefaultJsonProtocol.jsonFormat8
import spray.json.JsonFormat
import spray.json._
import DefaultJsonProtocol._
import ru.tinkoff.invest.openapi.models.market.Instrument

trait ElasticClass
object ModInstrument {

  case class EInstrument(figi: String,
                         ticker: String,
                         isin: String,
                         minPriceIncrement: BigDecimal,
                         lot: Int,
                         currency: String,
                         name: String,
                         instrumentType: String) extends ElasticClass

  implicit val instrumentToEInstrument: Instrument => EInstrument = instrument => {
    EInstrument(
      instrument.figi,
      instrument.ticker,
      instrument.isin,
      instrument.minPriceIncrement,
      instrument.lot,
      instrument.currency.name(),
      instrument.name,
      instrument.`type`.name()
    )
  }
  implicit val formatSearchPayment2: JsonFormat[EInstrument] = jsonFormat8(EInstrument)
}
