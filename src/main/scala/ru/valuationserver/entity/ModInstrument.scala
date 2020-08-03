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
                        // minPriceIncrement: String,
                         lot: Int,
                         currency: String,
                         name: String,
                         instrumentType: String) extends ElasticClass

  implicit val instrumentToEInstrument: Instrument => EInstrument = instrument => {
    EInstrument(
      instrument.figi,
      instrument.ticker,
      instrument.isin,
    //  instrument.minPriceIncrement.toString,
      instrument.lot,
      instrument.currency.name(),
      instrument.name,
      instrument.`type`.name()
    )
  }
  implicit val formatSearchPayment2: JsonFormat[EInstrument] = jsonFormat7(EInstrument)
}
