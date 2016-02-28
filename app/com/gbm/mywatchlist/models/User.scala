package com.gbm.mywatchlist.models

/**
  * Created by gbmetzner on 11/5/15.
  */
case class User(id: Option[String] = None, firstName: String, lastName: String, email: String, password: String)