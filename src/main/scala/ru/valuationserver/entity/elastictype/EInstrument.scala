package ru.valuationserver.entity.elastictype

import akka.Done
import akka.stream.Materializer
import akka.stream.alpakka.elasticsearch.WriteMessage
import akka.stream.alpakka.elasticsearch.scaladsl.ElasticsearchSink
import akka.stream.scaladsl.Source
import cats.effect.{ContextShift, IO}
import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import ru.tinkoff.invest.openapi.models.market.Instrument
import ru.valuationserver.entity.ElasticTable
import spray.json.DefaultJsonProtocol._
import spray.json._
import spray.json.DefaultJsonProtocol.jsonFormat7
import spray.json.JsonFormat
import DefaultJsonProtocol._

import scala.language.implicitConversions

case class EInstrument(figi: String,
                       ticker: String,
                       isin: String,
                       //minPriceIncrement: String,
                       lot: Int,
                       currency: String,
                       name: String,
                       instrumentType: String)

object Instrument {
  
  def (instrument: Instrument).toEInstrument = EInstrument(instrument.figi,
    instrument.ticker,
    instrument.isin,
    // instrument.minPriceIncrement.toString,
    instrument.lot,
    instrument.currency.name(),
    instrument.name,
    instrument.`type`.name()
  )

  //  implicit val format: JsonFormat[EInstrument] = jsonFormat8(EInstrument)

  //  given JsonFormat[EInstrument] = jsonFormat8(EInstrument)
  //  given Conversion[List[Instrument], List[EInstrument]] = _.map(p => instrumentToEInstrument(p))

}

