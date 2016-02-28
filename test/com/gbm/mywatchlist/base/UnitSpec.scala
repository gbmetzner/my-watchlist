package com.gbm.mywatchlist.base

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

/**
  * Created by gbmetzner on 11/15/15.
  */

abstract class UnitSpec
  extends FlatSpec
    with Matchers
    with OptionValues
    with Inside
    with Inspectors
    with ScalaFutures