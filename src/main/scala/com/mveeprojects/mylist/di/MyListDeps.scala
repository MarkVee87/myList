package com.mveeprojects.mylist.di

import akka.http.scaladsl.server.Route
import com.mveeprojects.mylist.config.MyListConfig
import com.mveeprojects.mylist.routes.MyListRoutes

trait MyListDeps {

  import com.softwaremill.macwire._

  lazy val routes: Route = wire[MyListRoutes].route
  lazy val config: MyListConfig = wire[MyListConfig]
}
