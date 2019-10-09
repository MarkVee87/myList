package com.mveeprojects.mylist.repo

import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.mveeprojects.mylist.di.ApiModules
import com.mveeprojects.mylist.model.Thing
import net.liftweb.json.Serialization.write
import net.liftweb.json.{DefaultFormats, parse}

class CouchbaseRepo extends ApiModules {

  implicit val formats: DefaultFormats.type = DefaultFormats

  private val cluster: CouchbaseCluster = CouchbaseCluster.create(config.couchbaseHostname)
  cluster.authenticate(config.couchbaseUsername, config.couchbasePassword)
  private val bucket: Bucket = cluster.openBucket(config.couchbaseBucketName)

  val example_thing = Thing("a", "b")
  val example_id = "1"

  def storeExampleThing(): Unit = {
    val doc: JsonObject = JsonObject.fromJson(write(example_thing))
    bucket.upsert(JsonDocument.create(example_id, doc))
  }

  def retrieveExampleThing(): Thing = {
    val result = bucket.get(example_id)
    parse(result.content().toString).extract[Thing]
  }
}