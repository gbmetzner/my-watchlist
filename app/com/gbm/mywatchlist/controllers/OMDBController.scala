package com.gbm.mywatchlist.controllers

import javax.inject.Inject

import com.gbm.mywatchlist.models.Movie
import play.api.i18n.MessagesApi
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.Action

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by gbmetzner on 2016-02-28.
  */
class OMDBController @Inject()(val messagesApi: MessagesApi, val ws: WSClient) extends BaseController {

  def search = Action.async {
    request =>

      import com.gbm.mywatchlist.utils.json.MovieParser.{readsOMDB, movieWrites}

      val title = request.getQueryString("title").getOrElse("Batman")

      logger debug s"Search for $title."

      ws.url(s"http://www.omdbapi.com/?s=$title").get().map {
        response =>
          val movies = (response.json \ "Search").as[List[Movie]]

          Ok(Json.obj("movies" -> Json.toJson(movies)))
      }
  }
}
