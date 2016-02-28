package com.gbm.mywatchlist.services

import com.gbm.mywatchlist.models.Page
import com.gbm.mywatchlist.utils.messages._
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author Gustavo Metzner on 10/16/15.
  */
trait Service[T] extends LazyLogging {

  type Predicate = Bson

  def persist(t: T)(f: T => Future[Result]): Future[Result] = {
    logger debug s"Persisting $t"
    f(t)
  }

  def findBy(predicate: Predicate)(f: Predicate => Future[List[T]]): Future[List[T]] = {
    logger debug s"Finding by $predicate"
    f(predicate)
  }

  def findOneBy(predicate: Predicate)(f: Predicate => Future[Option[T]]): Future[Option[T]] = {
    logger debug s"Finding one by $predicate"
    f(predicate)
  }

  def search(predicate: Predicate)(f: Predicate => Future[Option[Page[T]]]): Future[Option[Page[T]]] = {
    logger debug s"searching by $predicate"
    f(predicate)
  }

  def remove(id: String)(f: String => Future[Result])(g: Predicate => Future[Option[T]]): Future[Either[Failed, Succeed]] = {
    logger debug s"Removing id $id"

    findOneBy(Filters.eq("_id", id))(g).flatMap {
      case Some(result) => f(id).map {
        case error: Error =>
          logger error(s"Error while removing = $result", error.message)
          Left(error.copy(message = "error.general"))
        case _ => Right(Succeed("msg.user.added"))
      }
      case None => Future.successful(Left(Warning("msg.user.not.found")))
    }
  }
}
