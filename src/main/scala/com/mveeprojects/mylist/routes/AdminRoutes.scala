package com.mveeprojects.mylist.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.model.ListItem

class AdminRoutes extends ApiModules {
  val route: Route = pathPrefix("admin") {
    get {
      path("status") {
        complete("up")
      }
    } ~ get {
      path("storesampledata") {
        couchbaseRepo.storeExampleListItem()
        complete("sample data stored")
      }
    } ~ get {
      path("retrievesampledata") {
        val response: ListItem = couchbaseRepo.retrieveExampleListItem()
        complete(response.toString)
      }
    }
  }
}