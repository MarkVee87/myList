package com.mveeprojects.mylist.di

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.config.ApiConfig
import com.mveeprojects.mylist.exception.MyListExceptionHandler
import com.mveeprojects.mylist.repo.CouchbaseRepo
import com.mveeprojects.mylist.route.{AdminRoutes, ApiRoutes}
import com.mveeprojects.mylist.util.HealthCheckUtils

trait ApiModules extends MyListExceptionHandler {

  import com.softwaremill.macwire._

  lazy val config: ApiConfig = wire[ApiConfig]

  lazy val apiRoutes: Route = wire[ApiRoutes].route
  lazy val adminRoutes: Route = wire[AdminRoutes].route
  lazy val allRoutes: Route = Route.seal(apiRoutes ~ adminRoutes)

  lazy val couchbaseRepo: CouchbaseRepo = wire[CouchbaseRepo]
  lazy val healthCheckUtils: HealthCheckUtils = wire[HealthCheckUtils]
}