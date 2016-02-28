package com.gbm.mywatchlist.services

import com.gbm.mywatchlist.models.{Login, User}
import com.gbm.mywatchlist.utils.messages.Result

import scala.concurrent.Future

trait UserServiceComponent {

  def userService: UserService

  trait UserService extends Service[User] {
    def persist(user: User)(f: User => Future[Result])(g: Predicate => Future[Option[User]]): Future[Result]

    def authenticate(login: Login)(f: Predicate => Future[Option[User]]): Future[Option[User]]
  }

}