package com.gbm.mywatchlist.services

import com.gbm.mywatchlist.models.Movie

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait MovieServiceComponent {

  def movieService: MovieService

  trait MovieService extends Service[Movie]

}
