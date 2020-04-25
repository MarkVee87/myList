package com.mveeprojects.mylist.exception

import akka.http.scaladsl.model.{HttpResponse, StatusCode}
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.ExceptionHandler
import com.typesafe.scalalogging.LazyLogging

trait MyListExceptionHandler extends LazyLogging {

  def exceptionLogger(ex: MyListException): Unit = logger.info(s"[${ex.getClass.getSimpleName}] - ${ex.msg}")

  implicit def myExceptionHandler: ExceptionHandler = {
    ExceptionHandler {
      case ex: UserNotFoundException =>
        exceptionLogger(ex)
        complete(HttpResponse(StatusCode.int2StatusCode(404), entity = ex.message))
      case ex: DuplicateItemException =>
        exceptionLogger(ex)
        complete(HttpResponse(StatusCode.int2StatusCode(409), entity = ex.message))
    }
  }
}