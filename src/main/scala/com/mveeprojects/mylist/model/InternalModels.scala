package com.mveeprojects.mylist.model

case class ListItem(id: Int, description: String)

case class UsersList(userId: Int, itemList: List[ListItem])