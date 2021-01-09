package com.mveeprojects.mylist

import akka.http.scaladsl.Http
import com.mveeprojects.mylist.config.ActorSystemConfig
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.repo.couchbase.InitialiseCouchbase
import com.mveeprojects.mylist.utils.Logging

import scala.util.{Failure, Success}

object MyListMain extends App with ApiModules with ActorSystemConfig with Logging {

  logger.info("Initialising couchbase")

  InitialiseCouchbase.initialise

  logger.info("Couchbase cluster initialised, moving on to app startup.")
  val bindingFuture =
    Http().bindAndHandle(allRoutes, config.appConfig.http.hostname, config.appConfig.http.port)
  bindingFuture.onComplete {
    case Success(binding) =>
      logger.info(s"App is running -> ${binding.localAddress}")
    case Failure(exception) =>
      logger.info(s"App is dead -> ${exception.getMessage}")
  }
}
