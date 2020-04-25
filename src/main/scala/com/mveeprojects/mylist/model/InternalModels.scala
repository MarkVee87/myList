package com.mveeprojects.mylist.model

case class Item(uuid: String, description: String)

case class UsersList(userId: String, itemList: List[Item])