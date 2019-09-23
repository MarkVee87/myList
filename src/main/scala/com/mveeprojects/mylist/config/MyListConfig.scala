package com.mveeprojects.mylist.config

import com.typesafe.config.ConfigFactory

class MyListConfig {
  private val config = ConfigFactory.load()

  val appHostname: String = config.getString("mylist.hostname")
  val appPort: Int = config.getInt("mylist.port")
}