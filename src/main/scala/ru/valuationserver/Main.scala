package ru.valuationserver

import java.util.logging.Logger

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.effect.{ContextShift, ExitCode, IO, IOApp}
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory
import ru.valuationserver.core.ConfigObject.INVEST_TOKEN

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object Main extends IOApp {
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  override val contextShift: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- apiTask
    _ = Thread.sleep(40000)
  } yield ExitCode(1)

  val apiTask: IO[OpenApi] = for {
    log <- IO {Logger.getLogger("Pooo")}
    api <- IO {new OkHttpOpenApiFactory(INVEST_TOKEN, log).createOpenApiClient(ec)}
  } yield api
}
