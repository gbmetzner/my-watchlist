package com.gbm.mywatchlist.models

/**
  * Created by gbmetzner on 2016-02-28.
  */
case class Movie(id: Option[String] = None,
                 title: String,
                 year: String,
                 poster: String,
                 imdbId: String,
                 userId: Option[String] = None)
