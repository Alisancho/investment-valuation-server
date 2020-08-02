package ru.valuationserver

import java.util.logging.Logger

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.effect.{ContextShift, ExitCode, IO, IOApp}
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory
import ru.valuationserver.core.ConfigObject
import ru.valuationserver.core.ConfigObject.{INVEST_TOKEN, TINKOFF_BROKER_ACCOUNT_ID}
import ru.valuationserver.service.TinkoffApiService

import scala.concurrent.ExecutionContextExecutor

object Main extends IOApp {
  implicit val system: ActorSystem = ActorSystem("home-invest", ConfigObject.conf)
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  override val contextShift: ContextShift[IO] = IO.contextShift(ec)

  override def run(args: List[String]): IO[ExitCode] = for {
    api <- apiTask
    tin <- IO(new TinkoffApiService(api, TINKOFF_BROKER_ACCOUNT_ID))
    l   <- tin.getMarketStocks(contextShift)

  } yield ExitCode(1)

  val apiTask: IO[OpenApi] = for {
    log <- IO {Logger.getLogger("Pooo")}
    api <- IO {new OkHttpOpenApiFactory(INVEST_TOKEN, log).createOpenApiClient(ec)}
  } yield api
}
