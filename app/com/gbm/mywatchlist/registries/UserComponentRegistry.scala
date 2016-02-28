package com.gbm.mywatchlist.registries

import com.gbm.mywatchlist.repositories.impl.UserRepositoryComponentImpl
import com.gbm.mywatchlist.services.impl.UserServiceComponentImpl

/**
  * Created by gbmetzner on 12/8/15.
  */
trait UserComponentRegistry
  extends UserServiceComponentImpl
  with UserRepositoryComponentImpl
