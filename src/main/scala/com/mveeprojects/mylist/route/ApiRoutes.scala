package com.mveeprojects.mylist.route

import akka.http.scaladsl.model.StatusCode
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.di.ApiModules

class ApiRoutes extends ApiModules {
  val route: Route =
    pathPrefix("mylist") {
      concat(
        // also want an endpoint to show items in item bucket (only these can be added to list)
        get {
          path("items" / "all") {
            complete(StatusCode.int2StatusCode(200))
          }
        },
        get {
          path("retrieve" / Segment) { userId =>
            complete(StatusCode.int2StatusCode(200), couchbaseRepo.retrieveUsersList(userId).toString)
          }
        },
        put {
          path("additem" / Segment / Segment) { (userId, itemId) =>
            couchbaseRepo.addToUsersList(userId, itemId)
            complete(StatusCode.int2StatusCode(201), s"Added $itemId to $userId's list")
          }
        },
        put {
          path("createuser" / Segment) { userId =>
            complete(StatusCode.int2StatusCode(201), couchbaseRepo.createUser(userId).toString)
          }
        }
      )
    }

}