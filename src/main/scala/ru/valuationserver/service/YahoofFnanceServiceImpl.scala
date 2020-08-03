//package ru.valuationserver.service
//
//import akka.http.scaladsl.model.Uri
//import akka.http.scaladsl.model.Uri.Query
//import akka.actor.ActorSystem
//import akka.http.scaladsl.model._
//import akka.http.scaladsl.unmarshalling.Unmarshal
//import akka.http.scaladsl.Http
//import akka.stream.Materializer
//
//import scala.concurrent.Future
//
//object YahoofFnanceServiceImpl {
//
//  val URL_YAHOO = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol=IBM"
//
//  def get(url: String)(system: ActorSystem, materializer: Materializer): Future[String] =
//    Http()(system)
//      .singleRequest(HttpRequest(uri = url))
//      .flatMap(x => Unmarshal(x).to[String](materializer,))
//
//  val req: String => Uri = symbol =>
//    Uri("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol=IBM")
//      .withQuery(
//        Query(
//          "symbol" -> symbol,
//        )
//      )
//}
//
////class YahoofFnanceServiceImpl {
////
////  def wd(): Unit = {
////
////    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol=IBM")
////      .get()
////      .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
////      .addHeader("x-rapidapi-key", "287a72f0c2msh82c4ec948a0581ep1a4f1ejsnc4a8342cf56a")
////      .build();
////
////    Response response = client.newCall(request).execute();
////  }
////}
