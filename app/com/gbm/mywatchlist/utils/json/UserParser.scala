package com.gbm.mywatchlist.utils.json

import com.gbm.mywatchlist.models.{Login, User}
import com.gbm.mywatchlist.utils.Password._
import org.bson.BsonValue
import org.mongodb.scala.Document
import org.mongodb.scala.bson.BsonString
import play.api.libs.json.Reads._
import play.api.libs.json._

object UserParser {

  private val reads: Reads[User] = new Reads[User] {
    override def reads(json: JsValue): JsResult[User] = {
      val firstName = (json \ "firstName").as[String](minLength[String](2))
      val lastName = (json \ "lastName").as[String](minLength[String](2))
      val emaiL = (json \ "email").as[String](email)
      JsSuccess(User(firstName = firstName, lastName = lastName, email = emaiL, password = ""))
    }
  }

  private val writes: Writes[User] = new Writes[User] {
    override def writes(user: User): JsValue = {
      Json.obj(
        "id" -> user.id,
        "firstName" -> user.firstName,
        "lastName" -> user.lastName,
        "email" -> user.email
      )
    }
  }

  implicit val userFormatterController = Format(reads, writes)

  implicit val userFormatterRepo = Json.format[User]

  implicit val loginFormatterController = Json.format[Login]

  implicit val userDocumentFormat = new UserDocumentFormat

  class UserDocumentFormat extends DocumentFormat[User] {

    override protected[json] def extractFields(user: User): List[(String, BsonValue)] = {
      "firstName" -> BsonString(user.firstName) :: "lastName" -> BsonString(user.lastName) ::
        "email" -> BsonString(user.email) :: "password" -> BsonString(user.password.encryptPassword) ::
        user.id.map(id => "_id" -> BsonString(id) :: Nil).getOrElse(Nil)
    }

    override protected[json] def buildEntity(document: Document): User = {
      val id = Option(document("_id").asObjectId().getValue.toHexString)
      val firstName = document("firstName").asString().getValue
      val lastName = document("lastName").asString().getValue
      val email = document("email").asString().getValue
      User(id = id, firstName = firstName, lastName = lastName, email = email, password = "")
    }
  }

}
