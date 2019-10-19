package com.mveeprojects.mylist.model

case class ListItem(id: String, description: String)

case class UsersList(userId: String, itemList: List[ListItem])