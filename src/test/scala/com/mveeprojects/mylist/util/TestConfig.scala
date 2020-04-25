package com.mveeprojects.mylist.util

import com.typesafe.config.{Config, ConfigFactory}

trait TestConfig {
  val config: Config = ConfigFactory.load("testconfig")

  val testUserId_1: String = config.getString("testvars.testuserid_1")
  val testUserId_2: String = config.getString("testvars.testuserid_2")
  val testItemId: String = config.getString("testvars.testitemid")

  val appHostname: String = config.getString("mylist.hostname")
  val appPort: Int = config.getInt("mylist.port")

  val couchbaseUsername: String = config.getString("couchbase.username")
  val couchbasePassword: String = config.getString("couchbase.password")
  val couchbaseHostname: String = config.getString("couchbase.hostname")
  val couchbaseMyListBucketName: String = config.getString("couchbase.mylistBucketName")
  val couchbaseItemBucketName: String = config.getString("couchbase.itemBucketName")
}