package ru.valuationserver.service

import java.util.UUID

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import spray.json._
import akka.Done
import akka.stream.alpakka.elasticsearch.WriteMessage
import akka.stream.alpakka.elasticsearch.scaladsl.ElasticsearchSink
import org.apache.http.auth.AuthScope
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import ru.valuationserver.entity.ElasticClass

import scala.concurrent.Future

object ElasticsearchServiceImpl extends App {

  private def getID: String = "API-" + UUID.randomUUID().toString.substring(24)

  def getElasticSearchClient(host: String, port: Int): RestClient = {
    val credentialsProvider: CredentialsProvider = new BasicCredentialsProvider()
    credentialsProvider.getCredentials(AuthScope.ANY)
    RestClient
      .builder(new HttpHost(host, port))
      .setHttpClientConfigCallback(p => {
        p.disableAuthCaching
        p.setDefaultCredentialsProvider(credentialsProvider)
      })
      .build
  }

  def insert[T <: ElasticClass](elasticIndex: String, elasticTypeDoc: String, listData: List[T])
                               (restClient: RestClient, js: JsonFormat[T])
                               (materializer: Materializer): Future[Done] =
    Source(listData)
      .map { objectMess =>
        WriteMessage.createUpsertMessage(id = getID, source = objectMess)
      }.runWith(ElasticsearchSink.create[T](elasticIndex, elasticTypeDoc)(elasticsearchClient = restClient, sprayJsonWriter = js))(materializer)

}
