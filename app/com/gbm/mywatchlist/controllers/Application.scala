package com.gbm.mywatchlist.controllers

import play.api.mvc._

class Application extends Controller {

  def main(any: String) = Action {
    Ok(views.html.main())
  }

}
