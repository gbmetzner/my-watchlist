package com.gbm.mywatchlist.controllers

import javax.inject.Inject

import com.gbm.mywatchlist.models.User
import com.gbm.mywatchlist.registries.UserComponentRegistry
import com.gbm.mywatchlist.repositories.UserRepositoryComponent
import com.gbm.mywatchlist.services.UserServiceComponent
import com.gbm.mywatchlist.utils.json.UserParser._
import com.gbm.mywatchlist.utils.messages.Succeed
import play.api.i18n.MessagesApi
import play.api.mvc.Action

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by gbmetzner on 2016-02-28.
  */
class UserController @Inject()(val messagesApi: MessagesApi) extends BaseController with UserComponentRegistry {
  requires: UserServiceComponent with UserRepositoryComponent =>

  def create = Action.async(parse.json) {
    request =>

      request.body.validate[User].map {
        user =>
          userService.persist(user)(userRepository.insert)(userRepository.findOneBy).map {
            case Succeed(msg) => Ok(withMessage(msg))
            case r => BadRequest(withMessage(r.message))
          }
      }.getOrElse(Future.successful(BadRequest(withMessage("error.invalid.json"))))
  }

}
