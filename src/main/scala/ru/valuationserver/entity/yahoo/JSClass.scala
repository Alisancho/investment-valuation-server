package ru.valuationserver.entity.yahoo
//MAIN
case class QuoteSummary(result: Seq[Result], error: String)

case class PreMarketChangePercent(raw: Double, fmt: String)

case class Price(maxAge: Int,
                 preMarketChangePercent: PreMarketChangePercent,
                 preMarketChange: PreMarketChangePercent,
                 preMarketTime: Int,
                 preMarketPrice: PreMarketChangePercent,
                 preMarketSource: String,
                 postMarketChange: Any,
                 postMarketPrice: Any,
                 regularMarketChangePercent: PreMarketChangePercent,
                 regularMarketChange: PreMarketChangePercent,
                 regularMarketTime: Int,
                 priceHint: PriceHint,
                 regularMarketPrice: PreMarketChangePercent,
                 regularMarketDayHigh: PreMarketChangePercent,
                 regularMarketDayLow: PreMarketChangePercent,
                 regularMarketVolume: PriceHint,
                 averageDailyVolume10Day: Any,
                 averageDailyVolume3Month: Any,
                 regularMarketPreviousClose: PreMarketChangePercent,
                 regularMarketSource: String,
                 regularMarketOpen: PreMarketChangePercent,
                 strikePrice: Any,
                 openInterest: Any,
                 exchange: String,
                 exchangeName: String,
                 exchangeDataDelayedBy: Int,
                 marketState: String,
                 quoteType: String,
                 symbol: String,
                 underlyingSymbol: String,
                 shortName: String,
                 longName: String,
                 currency: String,
                 quoteSourceName: String,
                 currencySymbol: String,
                 fromCurrency: String,
                 toCurrency: String,
                 lastMarket: String,
                 volume24Hr: Any,
                 volumeAllCurrencies: Any,
                 circulatingSupply: Any,
                 marketCap: PriceHint)

case class PriceHint(raw: Int, fmt: String, longFmt: String)



case class Result(price: Price)

case class RootInterface(quoteSummary: QuoteSummary)
