package com.gbm.mywatchlist.repositories

import java.util.UUID

import com.gbm.mywatchlist.models.Page
import com.gbm.mywatchlist.services.Service
import com.gbm.mywatchlist.utils.Config
import com.gbm.mywatchlist.utils.json.DocumentFormat
import com.gbm.mywatchlist.utils.messages.Result
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.Future

/**
  * Created by gbmetzner on 12/5/15.
  */
trait Repository[T] extends LazyLogging {

  type Predicate = Service[T]#Predicate

  /** The collection's name.
    *
    * @return The name of the collection.
    */
  protected[repositories] def collectionName: String

  /** The mongodb's collection.
    *
    * @return The [[org.mongodb.scala.MongoCollection]] to run the actions.
    */
  protected[repositories] def collection: MongoCollection[Document] = MongoContext.db.getCollection[Document](collectionName)

  /** Insert a [[T]].
    *
    * @param t The [[T]] to be inserted.
    * @return A [[Future]] of [[Result]]
    */
  def insert(t: T)(implicit format: DocumentFormat[T]): Future[Result]

  /** Update a [[T]].
    *
    * @param id The [[UUID]] of the [[T]] to be updated.
    * @param t  The [[T]] to be updated.
    * @return A [[Future]] of [[Result]]
    */
  def update(id: UUID, t: T)(implicit format: DocumentFormat[T]): Future[Result]

  /** Find [[T]]'s by a predicate.
    *
    * @param predicate The [[Predicate]] to the filter be based on.
    * @return A [[Future]] of [[List]] of a [[T]].
    */
  def findBy(predicate: Predicate)(implicit format: DocumentFormat[T]): Future[List[T]]

  /** Find only one [[T]].
    *
    * @param predicate The [[Predicate]] to the filter be based on.
    * @return A [[Future]] of [[Option]] of a [[T]].
    */
  def findOneBy(predicate: Predicate)(implicit format: DocumentFormat[T]): Future[Option[T]]

  /** Remove a [[T]].
    *
    * @param id The [[UUID]] of the [[T]].
    * @return A [[Future]] of [[Result]]
    */
  def remove(id: String): Future[Result]

  /** Search [[T]]'s by a predicate.
    *
    * @param predicate The [[Predicate]] to the filter be based on.
    * @return A [[Future]] of [[Option]] of [[Page]] of a [[T]].
    */
  def search(predicate: Predicate)(implicit format: DocumentFormat[T]): Future[Option[Page[T]]]

}

object MongoContext {

  import Config._

  private val mongoClient: MongoClient = MongoClient(dbConnectionUrl)
  private[repositories] val db: MongoDatabase = mongoClient.getDatabase(dbName)

  def collection(collectionName: String): MongoCollection[Document] =
    db.getCollection(collectionName)
}