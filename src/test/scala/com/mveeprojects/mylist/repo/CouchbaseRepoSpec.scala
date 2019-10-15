package com.mveeprojects.mylist.repo

import com.mveeprojects.mylist.model.UsersList
import com.mveeprojects.mylist.util.{CouchbaseUtils, RestAssuredUtils, TestConfig}
import io.restassured.response.Response
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class CouchbaseRepoSpec extends FeatureSpec with GivenWhenThen with Matchers with RestAssuredUtils with CouchbaseUtils with TestConfig {

  feature("Users list CRUD routes") {
    scenario("/createuser route should create a new user in the DB") {

      Given("Couchbase is running locally")
      checkLocalCouchbase.statusCode() shouldBe 200

      When(s"I call the /createuser endpoint with user id $testUserId")
      apiPutRequest(s"createuser/$testUserId")

      Then(s"The user with id $testUserId should exist in the DB")
      checkUserExists(testUserId) shouldBe true
    }

    // test failing due to issue with CouchbaseUtils.parseToUserList()
    scenario("/retrieve route should return a users list from the DB") {
      Given("Couchbase is running locally")
      checkLocalCouchbase.statusCode() shouldBe 200

      When(s"I call the /retrieve endpoint with user id $testUserId")
      val responseBody: Response = apiGetRequest(s"retrieve/$testUserId")

      Then(s"A UsersList with id $testUserId and an empty List should be returned")
      val testUsersList: UsersList = parseToUserList(responseBody)
      testUsersList.userId shouldBe testUserId
      testUsersList.itemList.size shouldBe 0
    }
    //    scenario("/additem route should update a users list in the DB")
  }
}