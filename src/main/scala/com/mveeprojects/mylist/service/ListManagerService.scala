package com.mveeprojects.mylist.service

import akka.Done

object ListManagerService {

  def addToList(uuid: String): Done = {
    // verify the uuid exists in items bucket if so add to list
    Done
  }
}
