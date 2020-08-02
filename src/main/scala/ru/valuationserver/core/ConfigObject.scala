package ru.valuationserver.core

import com.typesafe.config.{Config, ConfigFactory}

object ConfigObject {
  val conf: Config = ConfigFactory.load()
  val INVEST_TOKEN: String = conf.getString("invest_token")
  val SERVER_HOST: String = conf.getString("server.host")
  val SERVER_PORT: Int = conf.getInt("server.port")
  val TINKOFF_BROKER_ACCOUNT_ID: String = conf.getString("tinkoff.broker.account.id")
}
