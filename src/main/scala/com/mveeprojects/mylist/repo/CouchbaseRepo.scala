package com.mveeprojects.mylist.repo

import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.model.ListItem
import net.liftweb.json.Serialization.write
import net.liftweb.json.{DefaultFormats, parse}

class CouchbaseRepo extends ApiModules {

  implicit val formats: DefaultFormats.type = DefaultFormats

  private val cluster: CouchbaseCluster = CouchbaseCluster.create(config.couchbaseHostname)
  cluster.authenticate(config.couchbaseUsername, config.couchbasePassword)
  private val bucket: Bucket = cluster.openBucket(config.couchbaseBucketName)

  private val example_list_item = ListItem("some_name", "some_description")

  def storeListItem(userId: Int): Unit = {
    val doc: JsonObject = JsonObject.fromJson(write(example_list_item))
    bucket.upsert(JsonDocument.create(userId.toString, doc))
  }

  def retrieveListItem(userId: Int): ListItem = {
    val result = bucket.get(userId.toString)
    parse(result.content().toString).extract[ListItem]
  }
}