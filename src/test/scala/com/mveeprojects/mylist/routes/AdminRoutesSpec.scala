package com.mveeprojects.mylist.routes

import com.mveeprojects.mylist.util.RestAssuredUtils
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class AdminRoutesSpec extends FeatureSpec with GivenWhenThen with Matchers with RestAssuredUtils {

  feature("admin GET routes should return 200") {
    scenario("admin/status endpoint should return 200") {
      When("I send a request to the admin/status endpoint")
      val response = adminRequest("status")
      Then("I should receive a 200 response with message body \"up\"")
      response.statusCode() shouldBe 200
    }
  }
}