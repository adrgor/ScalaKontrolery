package repositiories

import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer

class CategoriesRepository {
  var categories = ListBuffer[String]()

  def addToCategoryList(category: String): Unit = {
    categories += category.toUpperCase()
  }

  def getCategoryList = {
    Json.toJson(categories.toList)
  }

  def getCategoryById(id: Int) = {
    Json.toJson(categories.toList(id))
  }

  def removeFromList(index: Int): Unit = {
    categories = categories.patch(index, Nil, 1)
  }

  def updateElement(index: Int, category: String): Unit = {
    categories = categories.updated(index, category.toUpperCase)
  }
}
