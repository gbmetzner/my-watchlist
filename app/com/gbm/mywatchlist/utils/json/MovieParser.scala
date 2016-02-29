package com.gbm.mywatchlist.utils.json

import com.gbm.mywatchlist.models.{Movie, Login, User}
import com.gbm.mywatchlist.utils.Password._
import org.bson.BsonValue
import org.mongodb.scala.Document
import org.mongodb.scala.bson.BsonString
import play.api.libs.json.Reads._
import play.api.libs.json._

object MovieParser {

  implicit val readsOMDB: Reads[Movie] = new Reads[Movie] {
    override def reads(json: JsValue): JsResult[Movie] = {
      val title = json \ "Title"
      val year = json \ "Year"
      val poster = json \ "Poster"
      val imdbId = json \ "imdbID"

      JsSuccess(Movie(title = title.as[String], year = year.as[String], poster = poster.as[String], imdbId = imdbId.as[String]))
    }
  }

  implicit val movieWrites = Json.writes[Movie]

  implicit val movieFormatter = Json.format[Movie]

  implicit val movieDocumentFormat = new MovieDocumentFormat

  class MovieDocumentFormat extends DocumentFormat[Movie] {

    override protected[json] def extractFields(movie: Movie): List[(String, BsonValue)] = {
      "title" -> BsonString(movie.title) :: "year" -> BsonString(movie.year) ::
        "poster" -> BsonString(movie.poster) :: "imdbId" -> BsonString(movie.imdbId) ::
        movie.userId.map(id => "userId" -> BsonString(id)).get ::
        movie.id.map(id => "_id" -> BsonString(id) :: Nil).getOrElse(Nil)
    }

    override protected[json] def buildEntity(document: Document): Movie = {
      val id = Option(document("_id").asObjectId().getValue.toHexString)
      val title = document("title").asString().getValue
      val year = document("year").asString().getValue
      val poster = document("poster").asString().getValue
      val imdbId = document("imdbId").asString().getValue
      val userId = Option(document("userId").asString().getValue)
      Movie(id = id, title = title, year = year, poster = poster, imdbId = imdbId, userId = userId)
    }
  }

}
