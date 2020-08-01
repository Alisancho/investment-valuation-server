package ru.valuationserver.service.tinkoffrest

import cats.effect.{ContextShift, IO}
import ru.tinkoff.invest.openapi.models.portfolio.{Portfolio, PortfolioCurrencies}

trait TinkoffPortfolio extends Tinkoff {
  val accountId: String

  def getPortfolio(contextShift: ContextShift[IO]): IO[Portfolio] = IO.fromFuture {
    IO(toScala(api.getPortfolioContext.getPortfolio(accountId)))
  }(contextShift)

  def getPortfolioCurrencies(contextShift: ContextShift[IO]): IO[PortfolioCurrencies] = IO.fromFuture {
    IO(toScala(api.getPortfolioContext.getPortfolioCurrencies(accountId)))
  }(contextShift)
}