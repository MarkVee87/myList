package com.mveeprojects.mylist.util

import io.restassured.RestAssured
import io.restassured.response.Response

trait RestAssuredUtils {

  def adminRequest(path: String): Response = {
    RestAssured.when().get(s"http://localhost:8080/admin/$path")
  }

  def apiRequest(path: String): Response = {
    RestAssured.when().get(s"http://localhost:8080/mylist/$path")
  }
}