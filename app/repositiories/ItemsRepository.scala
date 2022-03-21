package repositiories

import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer

class ItemsRepository {
    var items = ListBuffer[String]()

    def addToItemList(item: String): Unit = {
        items += item.toUpperCase
    }

    def getItemList = {
        Json.toJson(items.toList)
    }

    def getItemById(id: Int): String = {
        items.toList(id)
    }

    def removeFromList(index: Int): Unit = {
        items = items.patch(index, Nil, 1)
    }

    def updateElement(index: Int, item: String): Unit = {
        items = items.updated(index, item.toUpperCase)
    }
}
