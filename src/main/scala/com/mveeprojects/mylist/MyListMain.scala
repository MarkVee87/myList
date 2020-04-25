package com.mveeprojects.mylist

import akka.http.scaladsl.Http
import com.mveeprojects.mylist.config.ActorSystemConfig
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.repo.InitialiseCouchbase
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success}

object MyListMain extends App with ApiModules with ActorSystemConfig with LazyLogging {

  val initialised = InitialiseCouchbase.initialise

  if (!initialised) {
    logger.info("Initialisation of Couchbase cluster failed, check Couchbase is running and try again.")
  } else {
    logger.info("Couchbase cluster initialised, moving on to app startup.")
    val bindingFuture = Http().bindAndHandle(allRoutes, config.appHostname, config.appPort)
    bindingFuture.onComplete {
      case Success(binding) =>
        logger.info(s"App is running -> ${binding.localAddress}")
      case Failure(exception) =>
        logger.info(s"App is dead -> ${exception.getMessage}")
    }
  }
}