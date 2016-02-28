package com.gbm.mywatchlist.utils.json

import org.bson.BsonValue
import org.mongodb.scala.Document

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait DocumentFormat[T] {
  def write(t: T): Document = Document(extractFields(t))

  protected[json] def extractFields(t: T): List[(String, BsonValue)]

  def read(document: Document): T = buildEntity(document)

  protected[json] def buildEntity(document: Document): T

}
