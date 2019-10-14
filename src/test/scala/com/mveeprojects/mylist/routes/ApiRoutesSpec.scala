package com.mveeprojects.mylist.routes

import com.mveeprojects.mylist.util.RestAssuredUtils
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class ApiRoutesSpec extends FeatureSpec with GivenWhenThen with Matchers with RestAssuredUtils {

  private val testUserId = 1234

  feature("MyList GET routes should return 200") {
    scenario("mylist/retrieve endpoint should return 200") {
      Given("the app is running")
      adminRequest("status").statusCode() shouldBe 200
      When(s"I send a request to the mylist/retrieve endpoint with userId $testUserId")
      val response = apiRequest(s"retrieve/$testUserId")
      Then("I should receive a 200 response")
      response.statusCode() shouldBe 200
    }
  }
}