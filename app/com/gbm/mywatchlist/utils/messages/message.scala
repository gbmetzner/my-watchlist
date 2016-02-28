package com.gbm.mywatchlist.utils.messages

import play.api.mvc.{Result => PResult, Results}

/**
  * @author Gustavo Metzner on 10/11/15.
  */
sealed trait Result {
  type R = PResult
  val message: String
  val Status: R
}

sealed trait Failed extends Result

final case class Succeed(message: String) extends Result {
  override val Status: R = Results.Ok
}

final case class Warning(message: String) extends Failed {
  override val Status: R = Results.BadRequest
}

final case class Error(t: Throwable, message: String) extends Failed {
  override val Status: R = Results.BadRequest
}
