package com.gbm.mywatchlist.repositories

import com.gbm.mywatchlist.models.User

/** Defines a User Repository cake pattern component.
 *
  * @author Gustavo Metzner on 12/8/15.
  */
trait UserRepositoryComponent {

  /** A repository that runs actions on Users.
 *
    * @return The [[UserRepository]].
    */
  def userRepository: UserRepository

  trait UserRepository extends Repository[User]

}
