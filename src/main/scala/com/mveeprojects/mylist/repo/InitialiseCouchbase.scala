package com.mveeprojects.mylist.repo

import akka.Done
import com.couchbase.client.java.bucket.BucketType
import com.couchbase.client.java.cluster.DefaultBucketSettings
import com.mveeprojects.mylist.config.ActorSystemConfig
import com.mveeprojects.mylist.exception.DependencyInitialisationException

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object InitialiseCouchbase extends CouchbaseConnection with ActorSystemConfig {

// https://forums.couchbase.com/t/read-all-the-documents-from-a-bucket/5914/2
// In order to get all docs from the items bucket we will either need a PK (not good) or a simple view to omit all docs
// e.g. function (doc, meta) { emit(doc); }
// bucket.query(ViewQuery.from("test1", "all")).totalRows()

  def initialise: Done = {
    Await.result(upsertBucket(config.couchbaseMyListBucketName), 10 seconds)
  }

  private def upsertBucket(bucketName: String): Future[Done] = Future {
    val bucketSettings = DefaultBucketSettings.builder()
      .`type`(BucketType.COUCHBASE)
      .name(bucketName)
      .build()

    try {
      if (cluster.clusterManager().hasBucket(bucketSettings.name())) {
        logger.info(s"Bucket ${bucketSettings.name} already exits, updating config if needed")
        cluster.clusterManager().updateBucket(bucketSettings)
      } else {
        logger.info(s"Bucket ${bucketSettings.name} does not exist, creating bucket")
        cluster.clusterManager().insertBucket(bucketSettings)
      }
    } catch {
      case ex: Exception => throw DependencyInitialisationException(ex.getMessage)
    }
    Done
  }
}
