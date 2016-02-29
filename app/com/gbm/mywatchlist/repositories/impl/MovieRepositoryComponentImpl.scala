package com.gbm.mywatchlist.repositories.impl

import java.util.UUID

import com.gbm.mywatchlist.models.{Page, Movie}
import com.gbm.mywatchlist.repositories.MovieRepositoryComponent
import com.gbm.mywatchlist.utils.json.DocumentFormat
import com.gbm.mywatchlist.utils.messages.{Succeed, Result}
import org.mongodb.scala.model.Filters
import org.mongodb.scala.{Document, Completed, Observer}

import scala.concurrent.{Promise, Future}

/**
  * Created by gbmetzner on 2016-02-28.
  */
trait MovieRepositoryComponentImpl extends MovieRepositoryComponent {

  /** A repository that runs actions on Movies.
    *
    * @return The [[MovieRepository]].
    */
  override def movieRepository: MovieRepository = new MovieRepositoryImpl

  class MovieRepositoryImpl extends MovieRepository {

    override def insert(movie: Movie)(implicit format: DocumentFormat[Movie]): Future[Result] = {

      val promise: Promise[Result] = Promise()

      collection.insertOne(format.write(movie)).subscribe(new Observer[Completed] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = ()

        override def onNext(result: Completed): Unit = promise.success(Succeed("msg.movie.added"))
      })

      promise.future
    }

    /** Remove a [[Movie]].
      *
      * @param id The [[String]] of the [[Movie]].
      * @return A [[Future]] of [[Result]]
      */
    override def remove(id: String): Future[Result] = {

      val promise: Promise[Result] = Promise()

      collection.findOneAndDelete(Filters.eq("_id", id)).subscribe(new Observer[Document] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = ()

        override def onNext(result: Document): Unit = promise.success(Succeed("msg.movie.deleted"))
      })

      promise.future
    }

    override def search(predicate: Predicate)(implicit format: DocumentFormat[Movie]): Future[Option[Page[Movie]]] = {

      val promise: Promise[Option[Page[Movie]]] = Promise()

      collection.find(predicate).subscribe(new Observer[Document] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = promise.success(None)

        override def onNext(result: Document): Unit = promise.success(None)

      })
      promise.future
    }

    override def update(id: UUID, movie: Movie)(implicit format: DocumentFormat[Movie]): Future[Result] = ???

    override protected[repositories] def collectionName: String = "movies"


    override def findOneBy(predicate: Predicate)(implicit format: DocumentFormat[Movie]): Future[Option[Movie]] = ???

    override def findBy(predicate: Predicate)(implicit format: DocumentFormat[Movie]): Future[List[Movie]] = {
      var movies = List.empty[Movie]
      val promise: Promise[List[Movie]] = Promise()

      collection.find(predicate).subscribe(new Observer[Document] {
        override def onError(e: Throwable): Unit = promise.failure(e)

        override def onComplete(): Unit = promise.success(movies)

        override def onNext(result: Document): Unit =
          movies = format.read(result) :: movies
      })

      promise.future
    }
  }

}
