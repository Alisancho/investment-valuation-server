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
import cats.effect.{ContextShift, IO}
import org.apache.http.auth.AuthScope
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider

trait ElasticType

object ElasticsearchServiceImpl extends App {
  def getElasticSearchClient(host: String, port: Int): IO[RestClient] = IO {
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
}
class ElasticsearchServiceImpl{
  def insert[T <: ElasticType](k:T): Unit ={
    
  }
}