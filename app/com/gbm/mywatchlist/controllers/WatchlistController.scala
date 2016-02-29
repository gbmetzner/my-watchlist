package com.gbm.mywatchlist.controllers

import javax.inject.Inject

import com.gbm.mywatchlist.models.Movie
import com.gbm.mywatchlist.registries.MovieComponentRegistry
import com.gbm.mywatchlist.repositories.MovieRepositoryComponent
import com.gbm.mywatchlist.services.MovieServiceComponent
import com.gbm.mywatchlist.utils.json.MovieParser.{movieDocumentFormat, movieFormatter}
import com.gbm.mywatchlist.utils.messages.Succeed
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by gbmetzner on 2016-02-28.
  */
class WatchlistController @Inject()(val messagesApi: MessagesApi) extends BaseController with MovieComponentRegistry {
  requires: MovieServiceComponent with MovieRepositoryComponent =>

  def add = HasTokenAsync(parse.json) {
    token => user => request =>

      request.body.validate[Movie].map {
        movie =>
          movieService.persist(movie.copy(userId = user.id))(movieRepository.insert).map {
            case Succeed(msg) => Ok(withMessage(msg))
            case r => BadRequest(withMessage(r.message))
          }
      }.getOrElse(Future.successful(BadRequest(withMessage("error.invalid.json"))))
  }

}
