package com.mveeprojects.mylist.repo

import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.mveeprojects.mylist.di.ApiModules

trait CouchbaseConnection extends ApiModules {
  val cluster: CouchbaseCluster = CouchbaseCluster.create(config.couchbaseHostname)
  cluster.authenticate(config.couchbaseUsername, config.couchbasePassword)
  lazy val mylistBucket: Bucket = cluster.openBucket(config.couchbaseMyListBucketName)
  lazy val itemBucket: Bucket = cluster.openBucket(config.couchbaseMyListBucketName)
}