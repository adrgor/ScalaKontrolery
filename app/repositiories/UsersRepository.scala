package repositiories

import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer

class UsersRepository {
  var users = ListBuffer[String]()

  def addToUserList(category: String): Unit = {
    users += category.toUpperCase()
  }

  def getUserList = {
    Json.toJson(users.toList)
  }

  def getUserById(id: Int) = {
    Json.toJson(users.toList(id))
  }

  def removeFromList(index: Int): Unit = {
    users = users.patch(index, Nil, 1)
  }

  def updateElement(index: Int, category: String): Unit = {
    users = users.updated(index, category.toUpperCase)
  }
}
