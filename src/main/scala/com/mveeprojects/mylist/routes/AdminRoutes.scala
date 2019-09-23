package com.mveeprojects.mylist.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class AdminRoutes {
  val route: Route = pathPrefix("admin") {
    get {
      path("status") {
        complete("ok")
      }
    }
  }
}