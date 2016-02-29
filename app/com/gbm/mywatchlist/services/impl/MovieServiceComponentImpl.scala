package com.gbm.mywatchlist.services.impl

import com.gbm.mywatchlist.services.MovieServiceComponent

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait MovieServiceComponentImpl extends MovieServiceComponent {

  override def movieService: MovieService = new MovieServiceImpl

  class MovieServiceImpl extends MovieService {

  }

}
