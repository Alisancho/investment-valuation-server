package ru.valuationserver.service

import java.util.UUID
import java.util.function.Consumer

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import akka.stream.scaladsl.Source
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import spray.json._
import DefaultJsonProtocol._
import akka.Done
import akka.stream.alpakka.elasticsearch.{ElasticsearchWriteSettings, ReadResult, WriteMessage}
import akka.stream.alpakka.elasticsearch.scaladsl.{ElasticsearchSink, ElasticsearchSource}
import cats.effect.IO
import org.apache.http.auth.{AuthScope, UsernamePasswordCredentials}
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider


import scala.concurrent.Future


object ElasticsearchServiceImpl extends App {

  case class Company(id: String,
                     name: String,
                     tiker: String)

  val formatSearchPayment: JsonFormat[Company] = jsonFormat3(Company)

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

  def insert(elasticIndex: String, elasticTypeDoc: String, listData: List[Company])
            (restClient: RestClient, js: JsonFormat[Company])
            (materializer: Materializer): Future[Done] =
    Source(listData)
      .map { objectMess =>
        WriteMessage.createUpsertMessage(id = getID, source = objectMess)
      }.runWith(ElasticsearchSink.create[Company](elasticIndex, elasticTypeDoc)(elasticsearchClient = restClient, sprayJsonWriter = js))(materializer)

}
