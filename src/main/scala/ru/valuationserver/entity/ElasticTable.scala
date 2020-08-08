package ru.valuationserver.entity

import akka.Done
import akka.stream.Materializer
import akka.stream.alpakka.elasticsearch.WriteMessage
import akka.stream.alpakka.elasticsearch.scaladsl.ElasticsearchSink
import akka.stream.scaladsl.Source
import org.elasticsearch.client.RestClient
import spray.json.JsonFormat
import cats.effect.{ContextShift, IO}
import scala.concurrent.Future
import ru.valuationserver.entity.elastictype.EInstrument

trait ElasticDsl[F[_],G] {
  def create(g:G): F[Unit]
  def update(g:G): F[Unit]
  def delete(g:String):F[Unit]
  def find : F[Option[G]]
}

object Interpreter extends ElasticDsl[IO,EInstrument]{
  override def create(g: EInstrument): IO[Unit] = ???
  override def update(g: EInstrument): IO[Unit] = ???
  override def delete(g: String): IO[Unit] = ???
  override def find: IO[Option[EInstrument]] = ???
}

object ElasticTable{
  extension [G <: ElasticTable](elasticContainer:G) def insert(using materializer: Materializer,restClient: RestClient,sj:JsonFormat[T]): Future[Done] = Source(elasticContainer.obj :: Nil)
    .map { objectMess =>
      WriteMessage.createUpsertMessage(id = elasticContainer.id, source = objectMess)
    }.runWith(ElasticsearchSink.create[T](elasticContainer.elasticIndex, elasticContainer.elasticTypeDoc))

}