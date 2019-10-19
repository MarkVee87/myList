package com.mveeprojects.mylist.util

import com.typesafe.config.{Config, ConfigFactory}

trait TestConfig {
  val config: Config = ConfigFactory.load("testconfig")

  val testUserId: String = config.getString("testvars.testuserid")
  val testItemId: String = config.getString("testvars.testitemid")

  val appHostname: String = config.getString("mylist.hostname")
  val appPort: Int = config.getInt("mylist.port")

  val couchbaseUsername: String = config.getString("couchbase.username")
  val couchbasePassword: String = config.getString("couchbase.password")
  val couchbaseHostname: String = config.getString("couchbase.hostname")
  val couchbaseBucketName: String = config.getString("couchbase.bucketname")
}