package ru.valuationserver.entity

import java.util.UUID

import akka.Done
import akka.stream.Materializer
import akka.stream.alpakka.elasticsearch.WriteMessage
import akka.stream.alpakka.elasticsearch.scaladsl.ElasticsearchSink
import akka.stream.scaladsl.Source
import org.elasticsearch.client.RestClient
import ru.valuationserver.entity.elastictype.EInstrument
import ru.valuationserver.entity
import spray.json.DefaultJsonProtocol._
import spray.json.{JsonWriter, _}

import scala.concurrent.Future
import scala.language.implicitConversions


object ElasticContainer {
  given as JsonFormat[EInstrument] = jsonFormat7(EInstrument)
  

  extension (h:String) def ggeg(o:Int):String = h + o.toString
  

  
  given putToContainer [T <: ElasticTable]as Conversion[ElasticTable, ElasticContainer[T]] = _ match {
    case z: EInstrument => entity.ElasticContainer("instrument", "_doc", getID, z)
    case _ => throw RuntimeException("")
  }

  private def getID: String = "API-" + UUID.randomUUID().toString.substring(24)
}

