package com.mveeprojects.mylist.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route

class ApiRoutes {
  val route: Route = pathPrefix("mylist") {
    get {
      path("retrieve") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>retrieve route</h1>"))
      }
    }
  }
}