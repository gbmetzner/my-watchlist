package com.gbm.mywatchlist.utils

import com.typesafe.config.ConfigFactory
import play.api.Play.current

import scala.concurrent.duration.Duration

/**
  * @author Gustavo Metzner on 10/10/15.
  */
object Config {

  private val config = play.api.Play.configuration

  lazy val cacheDuration: Duration = getDuration("cache.expiration")

  lazy val dbName: String = getString("mongodb.name")

  lazy val dbConnectionUrl: String = getString("mongodb.url")

  def getString(key: String) = getStringFromKey(key)

  private def getDuration(key: String): Duration = Duration(getStringFromKey(key))

  private def getStringFromKey(key: String): String = {
    config.getString(key).getOrElse(throw new IllegalArgumentException(s"key $key not found."))
  }
}