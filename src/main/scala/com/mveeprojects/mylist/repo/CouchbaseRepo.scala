package com.mveeprojects.mylist.repo

import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.model.{ListItem, UsersList}
import net.liftweb.json.Serialization.write
import net.liftweb.json.{DefaultFormats, parse}

class CouchbaseRepo extends ApiModules {

  implicit val formats: DefaultFormats.type = DefaultFormats

  private val cluster: CouchbaseCluster = CouchbaseCluster.create(config.couchbaseHostname)
  cluster.authenticate(config.couchbaseUsername, config.couchbasePassword)
  private val bucket: Bucket = cluster.openBucket(config.couchbaseBucketName)

  def createUser(userId: Int): Unit = {
    val newuserlist = UsersList(userId, List.empty[ListItem])
    val doc: JsonObject = JsonObject.fromJson(write(newuserlist))
    bucket.upsert(JsonDocument.create(userId.toString, doc))
  }

  def addToUsersList(userId: Int, itemId: Int): Unit = {
    val oldUsersList: UsersList = retrieveUsersList(userId)
    val newUserList: UsersList = oldUsersList.copy(itemList = oldUsersList.itemList :+ ListItem(itemId, "blah"))
    val doc: JsonObject = JsonObject.fromJson(write(newUserList))
    bucket.upsert(JsonDocument.create(userId.toString, doc))
  }

  def retrieveUsersList(userId: Int): UsersList = {
    val result = bucket.get(userId.toString)
    parse(result.content().toString).extract[UsersList]
  }
}