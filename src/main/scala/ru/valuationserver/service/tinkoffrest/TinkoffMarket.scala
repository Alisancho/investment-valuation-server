package ru.valuationserver.service.tinkoffrest

import java.time.OffsetDateTime
import java.util.Optional

import cats.effect.{ContextShift, IO}
import ru.tinkoff.invest.openapi.models.market.{CandleInterval, HistoricalCandles, InstrumentsList}

trait TinkoffMarket extends Tinkoff {

  /**
   * Получение списка акций
   */
  def getMarketStocks(contextShift: ContextShift[IO]): IO[InstrumentsList] = IO.fromFuture(IO {
    toScala(api.getMarketContext.getMarketStocks)
  })(contextShift)


  /**
   * Получение списка ETF
   */
  def getMarketEtfs(contextShift: ContextShift[IO]): IO[InstrumentsList] = IO.fromFuture(IO {
    toScala(api.getMarketContext.getMarketEtfs)
  })(contextShift)

  /**
   * Получение списка облигаций
   */
  def getMarketBonds(contextShift: ContextShift[IO]): IO[InstrumentsList] = IO.fromFuture(IO {
    toScala(api.getMarketContext.getMarketBonds)
  })(contextShift)

  /**
   * Получение списка валютных пар
   */
  def getMarketCurrencies(contextShift: ContextShift[IO]): IO[InstrumentsList] = IO.fromFuture(IO {
    toScala(api.getMarketContext.getMarketCurrencies)
  })(contextShift)

  /**
   * Получение исторических свечей по FIGI
   */
  def getMarketCandles(figi: String)(contextShift: ContextShift[IO]): IO[Optional[HistoricalCandles]] = IO.fromFuture(IO {
    toScala(
      api.getMarketContext
        .getMarketCandles(
          figi,
          OffsetDateTime.now().minusDays(10),
          OffsetDateTime.now().minusDays(1),
          CandleInterval.DAY))
  }
  )(contextShift)
}
