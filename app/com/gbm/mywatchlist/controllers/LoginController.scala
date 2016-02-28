package com.gbm.mywatchlist.controllers

import javax.inject.Inject

import com.gbm.mywatchlist.models.Login
import com.gbm.mywatchlist.registries.UserComponentRegistry
import com.gbm.mywatchlist.repositories.UserRepositoryComponent
import com.gbm.mywatchlist.services.UserServiceComponent
import com.gbm.mywatchlist.utils.UUID.generate
import com.gbm.mywatchlist.utils.json.UserParser._
import play.api.i18n.MessagesApi
import play.api.libs.json.Json
import play.api.mvc.Action

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps

/**
  * Created by gbmetzner on 11/5/15.
  */
class LoginController @Inject()(val messagesApi: MessagesApi) extends BaseController with UserComponentRegistry {
  requires: UserServiceComponent with UserRepositoryComponent =>

  def login = Action.async(parse.json) {
    request =>

      request.body.validate[Login].map {
        login =>
          logger debug s"Login with = $login"
          userService.authenticate(login)(userRepository.findOneBy).map {
            case Some(user) =>
              val token = generate().toString
              Ok(Json.obj("authToken" -> token, "user" -> Json.toJson(user))).withToken(token -> user)
            case None => Unauthorized(Json.obj("msg" -> withMessage("login.invalid.credentials")))
          }
      }.getOrElse(Future.successful(BadRequest(withMessage("error.invalid.json"))))
  }

  def logout = Action.async {
    request =>
      Future {
        request.headers.get(AuthTokenHeader).map {
          token =>
            logger debug s"Logging out for token = $token"
            Ok(Json.obj("msg" -> withMessage("login.logged.out"))).discardingToken(token)
        } getOrElse {
          logger warn s"Token not found during logout."
          BadRequest(Json.obj("msg" -> withMessage("error.general")))
        }
      }
  }

  def logged = HasTokenAsync() {
    token => user => request =>
      logger debug s"Retrieving user data for $user"
      Future.successful(Ok(Json.obj("user" -> Json.toJson(user))))
  }

}