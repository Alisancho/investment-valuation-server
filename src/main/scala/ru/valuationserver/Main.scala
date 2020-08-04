package ru.valuationserver

import java.util.logging.Logger

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.effect.{ContextShift, ExitCode, IO, IOApp}
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory
import ru.valuationserver.core.ConfigObject
import ru.valuationserver.core.ConfigObject.{INVEST_TOKEN, TINKOFF_BROKER_ACCOUNT_ID}
import ru.valuationserver.service.{ElasticsearchServiceImpl, TinkoffApiService}
import akka.util.ccompat.JavaConverters._
import breeze.util.LazyLogger
import ru.valuationserver.entity.elastictype.Instrument._
import scala.concurrent.ExecutionContextExecutor
import scala.language.implicitConversions
import ru.valuationserver.entity.ElasticContainer.{given _}
import ru.valuationserver.entity.ElasticContainer._
import ru.valuationserver.entity.elastictype.EInstrument.{given _} 

object Main extends IOApp {
  given system as ActorSystem = ActorSystem("home-invest", ConfigObject.conf)
  given ec as ExecutionContextExecutor = system.dispatcher
  given materializer as ActorMaterializer = ActorMaterializer()
  override given contextShift as ContextShift[IO] = IO.contextShift(ec)

  override def run(args: List[String]): IO[ExitCode] = for {
    api   <- apiTask
    tin   <- IO(new TinkoffApiService(api, TINKOFF_BROKER_ACCOUNT_ID))
    l     <- tin.getMarketStocks(contextShift)
    resCl <- ElasticsearchServiceImpl.getElasticSearchClient("localhost", 9200)
    list  = l.instruments.asScala.toList.map(i => putToContainer(i.toEInstrument))
  
  
  } yield ExitCode(1)

  val apiTask: IO[OpenApi] = for {
    log <- IO {Logger.getLogger("Pooo")}
    api <- IO {new OkHttpOpenApiFactory(INVEST_TOKEN, log).createOpenApiClient(ec)}
  } yield api
  
}
