package com.mveeprojects.mylist.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.di.ApiModules

class ApiRoutes extends ApiModules {
  val route: Route =
    pathPrefix("mylist") {
      concat(
        get {
          path("retrieve" / Segment) { userId =>
            complete(StatusCodes.OK -> couchbaseRepo.retrieveUsersList(userId))
          }
        },
        put {
          path("additem" / Segment / Segment) { (userId, itemId) =>
            couchbaseRepo.addToUsersList(userId, itemId)
            complete(StatusCodes.Created -> s"Added $itemId to $userId's list")
          }
        },
        put {
          path("createuser" / Segment) { userId =>
            complete(StatusCodes.Created -> couchbaseRepo.createUser(userId).toString)
          }
        }
      )
    }
}
