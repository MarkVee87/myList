package com.mveeprojects.mylist.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route

class ApiRoutes {
  val route: Route = pathPrefix("mylist") {
    concat(
      get {
        path("retrieve" / IntNumber) { userId =>
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>retrieving $userId's items</h1>"))
        }
      },
      put {
        path("additem" / IntNumber) { userId =>
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, s"<h1>adding to $userId's items</h1>"))
        }
      }
    )
  }
}