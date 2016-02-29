package com.gbm.mywatchlist.registries

import com.gbm.mywatchlist.repositories.impl.MovieRepositoryComponentImpl
import com.gbm.mywatchlist.services.impl.MovieServiceComponentImpl

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait MovieComponentRegistry extends MovieServiceComponentImpl with MovieRepositoryComponentImpl
