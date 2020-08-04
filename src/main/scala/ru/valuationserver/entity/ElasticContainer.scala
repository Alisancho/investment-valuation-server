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

case class ElasticContainer[T <: ElasticTable](elasticIndex: String, elasticTypeDoc: String, id: String, obj: T)

object ElasticContainer {
  given as JsonFormat[EInstrument] = jsonFormat7(EInstrument)

  given Conversion[ElasticTable, ElasticContainer[_ <:ElasticTable]] = _ match {
    case z: EInstrument => entity.ElasticContainer("instrument", "_doc", getID, z)
    case _ => throw RuntimeException("")
  }
  
  def [T <: ElasticTable](elasticContainer:ElasticContainer[T]).insert(using materializer: Materializer,restClient: RestClient,sj:JsonFormat[T]): Future[Done] = Source(elasticContainer.obj :: Nil)
    .map { objectMess =>
      WriteMessage.createUpsertMessage(id = elasticContainer.id, source = objectMess)
    }.runWith(ElasticsearchSink.create[T](elasticContainer.elasticIndex, elasticContainer.elasticTypeDoc))
  private def getID: String = "API-" + UUID.randomUUID().toString.substring(24)
}

