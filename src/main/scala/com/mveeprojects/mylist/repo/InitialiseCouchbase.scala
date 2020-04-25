package com.mveeprojects.mylist.repo

import akka.Done
import com.couchbase.client.java.bucket.BucketType
import com.couchbase.client.java.cluster.DefaultBucketSettings
import com.mveeprojects.mylist.config.ActorSystemConfig

import scala.concurrent.Future

object InitialiseCouchbase extends CouchbaseConnection with ActorSystemConfig {

  def initialise: Done = {

    upsertBucket(config.couchbaseBucketName)
  }

  private def upsertBucket(bucketName: String): Future[Boolean] = Future {
    val bucketSettings = DefaultBucketSettings.builder()
      .`type`(BucketType.COUCHBASE)
      .name(bucketName)
      .build()

    try {
      if (cluster.clusterManager().hasBucket(bucketSettings.name())) {
        logger.info("1")
        cluster.clusterManager().updateBucket(bucketSettings)
      } else {
        logger.info("2")
        cluster.clusterManager().insertBucket(bucketSettings)
      }
    } catch {
      case _: Exception =>
        logger.info("wtf")
        return false
    }
    true
  }
}
