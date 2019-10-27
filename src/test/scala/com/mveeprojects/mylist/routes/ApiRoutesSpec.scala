package com.mveeprojects.mylist.routes

import com.mveeprojects.mylist.model.UsersList
import com.mveeprojects.mylist.util.{CouchbaseUtils, RestAssuredUtils, TestConfig}
import io.restassured.response.Response
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen, Matchers}

class ApiRoutesSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll with Matchers with RestAssuredUtils with CouchbaseUtils with TestConfig {

  override def beforeAll(): Unit = {
    Given(s"Couchbase is running locally")
    checkLocalCouchbase.statusCode() shouldBe 200
  }

  override def afterAll(): Unit = {
    Given(s"All previous data for the test user ($testUserId_1) has been deleted")
    deleteUsersListByUserId(testUserId_1) shouldBe true
  }

  feature("Users list CRUD routes") {
    scenario("/createuser route should create a new user in the DB") {
      When(s"I call the /createuser endpoint with user id $testUserId_1")
      apiPutRequest(s"createuser/$testUserId_1")
      Then(s"The user with id $testUserId_1 should exist in the DB")
      checkUserExists(testUserId_1) shouldBe true
    }

    scenario("/retrieve route should return a users list from the DB") {
      Given(s"The user with id $testUserId_1 exists in the DB")
      apiPutRequest(s"createuser/$testUserId_1")
      checkUserExists(testUserId_1) shouldBe true
      When(s"I call the /retrieve endpoint with user id $testUserId_1")
      val response: Response = apiGetRequest(s"retrieve/$testUserId_1")
      Then(s"A UsersList with id $testUserId_1 and an empty List shouldBe returned")
      val testUsersList: UsersList = parseToUserList(response)
      testUsersList.userId shouldBe testUserId_1
      testUsersList.itemList.size shouldBe 0
    }

    scenario("/additem route should update a users list in the DB") {
      Given(s"The user with id $testUserId_1 exists in the DB")
      apiPutRequest(s"createuser/$testUserId_1")
      checkUserExists(testUserId_1) shouldBe true
      When(s"I call the /additem endpoint with user id $testUserId_1 and itemId $testItemId")
      val response: Response = apiPutRequest(s"additem/$testUserId_1/$testItemId")
      Then("I should receive a 201 response with a confirmation message")
      response.statusCode shouldBe 201
      response.getBody.print shouldBe s"Added $testItemId to $testUserId_1's list"
    }

    scenario("Calling the /retrieve endpoint for a user that does not exist results in a 404") {
      Given("The test user does not exist in the DB")
      deleteUsersListByUserId(testUserId_2) shouldBe true
      checkUserExists(testUserId_2) shouldBe false
      When(s"I call the /retrieve endpoint with user id $testUserId_2")
      val response: Response = apiGetRequest(s"retrieve/$testUserId_2")
      Then("A 404 should be returned")
      response.statusCode() shouldBe 404
    }

    scenario("Calling the /addItem endpoint for an item that already exists in a given users list results in a 409") {
      Given(s"The user with id $testUserId_1 exists in the DB")
      apiPutRequest(s"createuser/$testUserId_1")
      checkUserExists(testUserId_1) shouldBe true
      When(s"I call the /additem endpoint with user id $testUserId_1 and itemId $testItemId")
      val response: Response = apiPutRequest(s"additem/$testUserId_1/$testItemId")
      Then("I should receive a 201 response with a confirmation message")
      response.statusCode shouldBe 201
      response.getBody.print shouldBe s"Added $testItemId to $testUserId_1's list"
      When(s"I call the /additem endpoint with user id $testUserId_1 and itemId $testItemId")
      val response1: Response = apiPutRequest(s"additem/$testUserId_1/$testItemId")
      Then("I should receive a 409 response with an exception message")
      response1.statusCode shouldBe 409
      response1.getBody.print shouldBe s"Item $testItemId already exists in user $testUserId_1's list"
    }
  }
}