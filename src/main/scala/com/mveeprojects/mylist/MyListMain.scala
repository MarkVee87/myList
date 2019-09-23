package com.mveeprojects.mylist

import akka.http.scaladsl.Http
import com.mveeprojects.mylist.config.MyListActors
import com.mveeprojects.mylist.di.MyListDeps

import scala.util.{Failure, Success}

object MyListMain extends App with MyListDeps with MyListActors {

  val bindingFuture = Http().bindAndHandle(routes, config.appHostname, config.appPort)

  bindingFuture.onComplete {
    case Success(binding) =>
      println(s"App is running -> ${binding.localAddress}")
    case Failure(exception) =>
      println(s"App is dead -> ${exception.getMessage}")
  }
}