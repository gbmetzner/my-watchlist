package com.gbm.mywatchlist.repositories.impl

import java.util.UUID

import com.gbm.mywatchlist.models.{Page, User}
import com.gbm.mywatchlist.repositories.UserRepositoryComponent
import com.gbm.mywatchlist.utils.json.DocumentFormat
import com.gbm.mywatchlist.utils.messages.{Result, Succeed}
import org.mongodb.scala.{Document, Completed, Observer}

import scala.concurrent.{Future, Promise}

trait UserRepositoryComponentImpl extends UserRepositoryComponent {

  /** A repository that runs actions on Users.
    *
    * @return The [[UserRepository]].
    */
  override def userRepository: UserRepository = new UserRepositoryImpl

  class UserRepositoryImpl extends UserRepository {

    /** The collection's name.
      *
      * @return The name of the collection.
      */
    override protected[repositories] def collectionName: String = "users"

    /** This function is not implemented by this Repository.
      */
    override def insert(user: User)(implicit format: DocumentFormat[User]): Future[Result] = {
      val promise: Promise[Result] = Promise()

      collection.insertOne(format.write(user)).subscribe(new Observer[Completed] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = ()

        override def onNext(result: Completed): Unit = promise.success(Succeed("msg.user.created"))
      })

      promise.future
    }

    /** This function is not implemented by this Repository.
      */
    override def update(id: UUID, user: User)(implicit format: DocumentFormat[User]): Future[Result] = throw new NotImplementedError("Update User not implemented.")

    /** This function is not implemented by this Repository.
      */
    override def findBy(predicate: Predicate)(implicit format: DocumentFormat[User]): Future[scala.List[User]] = throw new NotImplementedError("Update User not implemented.")

    /** Find only one [[User]].
      *
      * @param predicate The [[Predicate]] to the filter be based on.
      * @return A [[Future]] of [[Option]] of an [[User]].
      */
    override def findOneBy(predicate: Predicate)(implicit format: DocumentFormat[User]): Future[Option[User]] = {
      val promise: Promise[Option[User]] = Promise()

      collection.find(predicate).first().subscribe(new Observer[Document] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = promise.success(None)

        override def onNext(result: Document): Unit = promise.success(Some(format.read(result)))
      })

      promise.future
    }

    /** This function is not implemented by this Repository.
      */
    override def remove(id: String): Future[Result] = throw new NotImplementedError("Update User not implemented.")

    /** This function is not implemented by this Repository.
      */
    override def search(predicate: Predicate)(implicit format: DocumentFormat[User]): Future[Option[Page[User]]] = throw new NotImplementedError("Update User not implemented.")
  }

}
