package ru.valuationserver.service

object YahoofFnanceServiceImpl {
  val URL_YAHOO = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol=IBM"
}

class YahoofFnanceServiceImpl {

  def wd(): Unit = {

    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol=IBM")
      .get()
      .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
      .addHeader("x-rapidapi-key", "287a72f0c2msh82c4ec948a0581ep1a4f1ejsnc4a8342cf56a")
      .build();

    Response response = client.newCall(request).execute();
  }
}
