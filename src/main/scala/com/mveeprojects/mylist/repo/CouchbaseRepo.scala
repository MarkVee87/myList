package com.mveeprojects.mylist.repo

import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.mveeprojects.mylist.exceptions.{DuplicateItemException, UserNotFoundException}
import com.mveeprojects.mylist.model.{ListItem, UsersList}
import net.liftweb.json.Serialization.write
import net.liftweb.json.{DefaultFormats, parse}

class CouchbaseRepo extends CouchbaseConnection {

  implicit val formats: DefaultFormats.type = DefaultFormats

  def createUser(userId: String): Unit = {
    val doc: JsonObject = JsonObject.fromJson(write(UsersList(userId, List.empty[ListItem])))
    mylistbucket.upsert(JsonDocument.create(userId, doc))
  }

  def addToUsersList(userId: String, itemId: String): Unit = {
    val oldUsersList: UsersList = getUserListFromCouchbase(userId)
    if (isInUsersListAlready(oldUsersList, itemId))
      throw DuplicateItemException(s"Item $itemId already exists in user $userId's list")
    val newUserList: UsersList = oldUsersList.copy(itemList = oldUsersList.itemList :+ ListItem(itemId, "blah"))
    val doc: JsonObject        = JsonObject.fromJson(write(newUserList))
    mylistbucket.upsert(JsonDocument.create(userId, doc))
  }

  private def getUserListFromCouchbase(userId: String): UsersList =
    try parse(mylistbucket.get(userId).content().toString).extract[UsersList]
    catch {
      case _: NullPointerException => throw UserNotFoundException(s"User $userId does not exist in the database")
    }

  private def isInUsersListAlready(oldUsersList: UsersList, itemId: String): Boolean =
    if (oldUsersList.itemList.exists(_.id == itemId)) true
    else false

  def retrieveUsersList(userId: String): String =
    write(getUserListFromCouchbase(userId))
}
