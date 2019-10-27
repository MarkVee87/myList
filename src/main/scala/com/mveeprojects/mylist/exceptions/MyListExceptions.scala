package com.mveeprojects.mylist.exceptions

class MyListException(val msg: String) extends Exception(msg)

final case class UserNotFoundException(message: String) extends MyListException(msg = message)

final case class DuplicateItemException(message: String) extends MyListException(msg = message)
