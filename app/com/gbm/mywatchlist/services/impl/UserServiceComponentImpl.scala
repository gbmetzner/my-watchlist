package com.gbm.mywatchlist.services.impl

import java.util.NoSuchElementException
import com.gbm.mywatchlist.utils.Password._
import com.gbm.mywatchlist.models.{Login, User}
import com.gbm.mywatchlist.services.UserServiceComponent
import com.gbm.mywatchlist.utils.messages.{Failed, Result, Succeed, Warning}
import org.mongodb.scala.model.Filters

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserServiceComponentImpl extends UserServiceComponent {

  override def userService: UserService = new UserServiceImpl

  class UserServiceImpl extends UserService {

    override def persist(user: User)(f: (User) => Future[Result])(g: (Predicate) => Future[Option[User]]): Future[Result] = {
      logger debug s"Persisting user = $user"

      val futureEmailAvailable = g(Filters.eq("email", user.email)).map(_.isEmpty)

      (for {
        emailAvailable <- futureEmailAvailable
        created <- f(user) if emailAvailable
      } yield created).recover {
        case e: NoSuchElementException => Warning("email.not.available")
      }
    }

    override def authenticate(login: Login)(f: (Predicate) => Future[Option[User]]): Future[Option[User]] = {
      val filter = Filters.and(Filters.eq("email", login.email), Filters.eq("password", login.password.encryptPassword))
      f(filter)
    }

    override def remove(id: String)(f: (String) => Future[Result])(g: (Predicate) => Future[Option[User]]): Future[Either[Failed, Succeed]] = super.remove(id)(f)(g)
  }

}
