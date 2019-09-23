package com.mveeprojects.mylist.di

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.config.ApiConfig
import com.mveeprojects.mylist.routes.{AdminRoutes, ApiRoutes}

trait ApiModules {

  import com.softwaremill.macwire._

  lazy val apiRoutes: Route = wire[ApiRoutes].route
  lazy val adminRoutes: Route = wire[AdminRoutes].route
  lazy val allRoutes: Route = apiRoutes ~ adminRoutes
  lazy val config: ApiConfig = wire[ApiConfig]
}