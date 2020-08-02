package ru.valuationserver.service

import ru.tinkoff.invest.openapi.OpenApi
import ru.valuationserver.service.tinkoffrest.{TinkoffMarket, TinkoffOrders, TinkoffPortfolio}

class TinkoffApiService(val api: OpenApi, val accountId: String)
  extends TinkoffMarket with TinkoffOrders with TinkoffPortfolio {
}
