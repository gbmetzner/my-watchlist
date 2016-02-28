package com.gbm.mywatchlist.data

/**
  * Created by gbmetzner on 12/4/15.
  */
trait TestData[T] {

  protected var values: Map[String, T] = Map.empty[String, T]

  protected def add(pair: (String, T)): Unit = values = values + pair

  def gimme(key: String): T = values.getOrElse(key, throw new IllegalArgumentException(s"Key $key not found."))

}
