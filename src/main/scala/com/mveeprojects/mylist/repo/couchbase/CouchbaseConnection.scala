package com.mveeprojects.mylist.repo.couchbase

import com.couchbase.client.java.{Bucket, CouchbaseCluster}
import com.mveeprojects.mylist.di.ApiModules

trait CouchbaseConnection extends ApiModules {
  lazy val mylistbucket: Bucket = cluster.openBucket(config.appConfig.couchbase.bucketname)
  val cluster: CouchbaseCluster = CouchbaseCluster.create(config.appConfig.couchbase.hostname)
  cluster.authenticate(config.appConfig.couchbase.username, config.appConfig.couchbase.password)
}
