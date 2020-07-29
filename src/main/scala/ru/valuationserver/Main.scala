package ru.valuationserver

import cats.effect.{ExitCode, IO, IOApp}


object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- IO.unit
    _ = Thread.sleep(40000)
  } yield ExitCode(1)

}
