package com.mveeprojects.mylist.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.di.ApiModules

class ApiRoutes extends ApiModules {
  val route: Route = pathPrefix("mylist") {
    concat(
      get {
        path("retrieve" / IntNumber) { userId =>
          complete(couchbaseRepo.retrieveUsersList(userId).toString)
        }
      },
      put {
        path("additem" / IntNumber / IntNumber) { (userId, itemId) =>
          couchbaseRepo.addToUsersList(userId, itemId)
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, s"<h1>added $itemId to $userId's items</h1>"))
        }
      },
      put {
        path("createuser" / IntNumber) { userId =>
          complete(couchbaseRepo.createUser(userId).toString)
        }
      }
    )
  }
}