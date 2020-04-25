package com.mveeprojects.mylist.exception

class MyListException(val msg: String) extends Exception(msg)

final case class DependencyInitialisationException(message: String) extends MyListException(msg = message)

final case class UserNotFoundException(message: String) extends MyListException(msg = message)

final case class DuplicateItemException(message: String) extends MyListException(msg = message)
