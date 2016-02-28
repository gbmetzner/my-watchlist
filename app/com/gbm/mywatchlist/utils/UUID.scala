package com.gbm.mywatchlist.utils

/** This package provides methods to deal with UUID */
object UUID {

  /** Generates a new UUID */
  def generate(): java.util.UUID = java.util.UUID.randomUUID()

  def generateAsString(): String = java.util.UUID.randomUUID().toString

  def fromString(uuid: String): java.util.UUID = java.util.UUID.fromString(uuid)

  def actorId(name: String): String = s"$name-${generateAsString()}"
}
