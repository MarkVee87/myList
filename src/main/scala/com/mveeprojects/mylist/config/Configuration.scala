package com.mveeprojects.mylist.config

import pureconfig.ConfigSource
import pureconfig.generic.auto._

class Configuration {
  case class HttpConfig(hostname: String, port: Int)

  case class CouchbaseConfig(
      hostname: String,
      clustername: String,
      username: String,
      password: String,
      bucketname: String
  )

  case class MylistConfig(http: HttpConfig, couchbase: CouchbaseConfig)

  val appConfig: MylistConfig = ConfigSource.default.loadOrThrow[MylistConfig]
}
