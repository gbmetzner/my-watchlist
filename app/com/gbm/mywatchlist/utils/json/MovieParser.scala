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

}
