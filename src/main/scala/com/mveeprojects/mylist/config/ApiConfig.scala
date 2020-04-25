package com.mveeprojects.mylist.config

import com.typesafe.config.ConfigFactory

class ApiConfig {
  private val config = ConfigFactory.load()

  val appHostname: String = config.getString("mylist.hostname")
  val appPort: Int = config.getInt("mylist.port")

  val couchbaseUsername: String = config.getString("couchbase.username")
  val couchbasePassword: String = config.getString("couchbase.password")
  val couchbaseHostname: String = config.getString("couchbase.hostname")
  val couchbaseMyListBucketName: String = config.getString("couchbase.mylistBucketName")
  val couchbaseItemBucketName: String = config.getString("couchbase.itemBucketName")
}