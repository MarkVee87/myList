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

  val example_list_item = ListItem("some_name", "some_description")
  val example_list_item_id = "1"

  def storeExampleListItem(): Unit = {
    val doc: JsonObject = JsonObject.fromJson(write(example_list_item))
    bucket.upsert(JsonDocument.create(example_list_item_id, doc))
  }

  def retrieveExampleListItem(): ListItem = {
    val result = bucket.get(example_list_item_id)
    parse(result.content().toString).extract[ListItem]
  }
}