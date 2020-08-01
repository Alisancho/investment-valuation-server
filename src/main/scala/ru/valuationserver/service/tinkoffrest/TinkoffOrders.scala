package ru.valuationserver.service.tinkoffrest

import cats.effect.{ContextShift, IO}
import ru.tinkoff.invest.openapi.models.orders.{LimitOrder, MarketOrder, PlacedOrder}

trait TinkoffOrders extends Tinkoff {
  val accountId: String

  /**
   * Размещение лимитной заявки.
   *
   * @param figi       Идентификатор инструмента.
   * @param limitOrder Параметры отправляемой заявки.
   * @return Размещённая заявка.
   */
  def limitOrders(figi: String, limitOrder: LimitOrder)(contextShift: ContextShift[IO]): IO[PlacedOrder] = IO.fromFuture {
    IO(toScala(api.getOrdersContext.placeLimitOrder(figi, limitOrder, accountId)))
  }(contextShift)

  /**
   * Размещение рыночной заявки.
   *
   * @param figi        Идентификатор инструмента.
   * @param marketOrder Параметры отправляемой заявки.
   * @return Размещённая заявка.
   */
  def marketOrders(figi: String, marketOrder: MarketOrder)(contextShift: ContextShift[IO]): IO[PlacedOrder] = IO.fromFuture {
    IO(toScala(api.getOrdersContext.placeMarketOrder(figi, marketOrder, accountId)))
  }(contextShift)
}