package com.mveeprojects.mylist.util

import io.restassured.RestAssured
import io.restassured.response.Response

trait RestAssuredUtils {

  def adminGetRequest(path: String): Response =
    RestAssured.when().get(s"http://localhost:80/admin/$path")

  def apiGetRequest(path: String): Response =
    RestAssured.when().get(s"http://localhost:80/mylist/$path")

  def apiPutRequest(path: String): Response =
    RestAssured.when().put(s"http://localhost:80/mylist/$path")

  def checkLocalCouchbase: Response =
    RestAssured.when().get("http://localhost:8091/ui/index.html")
}
