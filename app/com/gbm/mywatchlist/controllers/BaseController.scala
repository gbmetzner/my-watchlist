package com.gbm.mywatchlist.controllers

import com.gbm.mywatchlist.controllers.security.Security
import com.typesafe.scalalogging.LazyLogging
import play.api.i18n.MessagesApi
import play.api.mvc.Controller

/**
  * @author Gustavo Metzner on 10/10/15.
  */
trait BaseController
  extends Controller with Security with LazyLogging {

  val messagesApi: MessagesApi

  def withMessage(key: String): String = messagesApi(key)

  def withMessage(key: String, arguments: Any*): String = messagesApi(key, arguments)
}

