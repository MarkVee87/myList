package com.mveeprojects.mylist.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.di.ApiModules

class AdminRoutes extends ApiModules {
  val route: Route = pathPrefix("admin") {
    get {
      path("status") {
        complete("up")
      }
    }
  }
}