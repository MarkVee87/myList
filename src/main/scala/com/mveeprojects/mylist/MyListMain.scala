package com.mveeprojects.mylist

import akka.http.scaladsl.Http
import com.mveeprojects.mylist.config.ActorSystemConfig
import com.mveeprojects.mylist.di.ApiModules

import scala.util.{Failure, Success}

object MyListMain extends App with ApiModules with ActorSystemConfig {

  val bindingFuture = Http().bindAndHandle(allRoutes, config.appHostname, config.appPort)

  bindingFuture.onComplete {
    case Success(binding) =>
      println(s"App is running -> ${binding.localAddress}")
    case Failure(exception) =>
      println(s"App is dead -> ${exception.getMessage}")
  }
}