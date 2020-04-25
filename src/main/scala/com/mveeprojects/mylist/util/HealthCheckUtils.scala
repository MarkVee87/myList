package com.mveeprojects.mylist.util

import com.mveeprojects.mylist.repo.CouchbaseConnection

class HealthCheckUtils extends CouchbaseConnection {

  def getBucketName: String = {
    mylistBucket.bucketManager().info().name()
  }
}