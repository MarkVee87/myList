package com.mveeprojects.mylist.util

import com.couchbase.client.java.error.DocumentDoesNotExistException
import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.mveeprojects.mylist.model.UsersList
import io.restassured.response.Response
import net.liftweb.json.{DefaultFormats, parse}

trait CouchbaseUtils extends TestConfig {

  implicit val formats: DefaultFormats.type = DefaultFormats

  private val cluster: CouchbaseCluster = CouchbaseCluster.create(couchbaseHostname)
  cluster.authenticate(couchbaseUsername, couchbasePassword)
  private val bucket: Bucket = cluster.openBucket(couchbaseBucketName)

  def deleteUsersListByUserId(userId: Int): Boolean = {
    try {
      bucket.remove(userId.toString)
      true
    } catch {
      case _: DocumentDoesNotExistException => true
      case _: Exception => false
    }
  }

  def checkUserExists(userId: String): Boolean = {
    Option(bucket.get(userId)) match {
      case Some(_) => true
      case None => false
    }
  }

  def parseToUserList(response: Response): UsersList = {
    val responseBody: String = response.getBody.prettyPrint
    parse(responseBody).extract[UsersList]
  }
}