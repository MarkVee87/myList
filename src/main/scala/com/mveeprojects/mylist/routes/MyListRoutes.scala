package com.mveeprojects.mylist.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging

class MyListRoutes extends LazyLogging {
  val route: Route =
    get {
      path("hello") {
        logger.info("received")
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Hello, world</h1>"))
      }
    }
}