package com.gbm.mywatchlist.repositories

import com.gbm.mywatchlist.models.Movie

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait MovieRepositoryComponent {

  /** A repository that runs actions on Movies.
    *
    * @return The [[MovieRepository]].
    */
  def movieRepository: MovieRepository

  trait MovieRepository extends Repository[Movie]

}
