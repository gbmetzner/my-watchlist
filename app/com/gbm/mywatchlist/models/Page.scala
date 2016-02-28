package com.gbm.mywatchlist.models

/**
  * @author Gustavo Metzner on 10/26/15.
  */
case class Page[E](totalRecords: Int, items: Seq[E])
