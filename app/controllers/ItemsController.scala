package controllers

import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}
import repositiories.ItemsRepository

import javax.inject._

@Singleton
class ItemsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val itemsRepository = new ItemsRepository

//  var form: Form[ItemFormInput] = {
//    Form(
//      mapping(
//        "name" -> nonEmptyText,
//      )(ItemFormInput.apply)(ItemFormInput.unapply)
//    )
//  }

  def getAllItems = Action {
    Ok(itemsRepository.getItemList.toString)
  }

  def getItemById(id: Int) = Action {
    Ok(itemsRepository.getItemById(id).toString)
  }

  def createNewItem = Action(parse.json) { implicit request =>
    val name = request.body("name").toString.replaceAll("\"", "")
    itemsRepository.addToItemList(name)
    Ok(name)
  }

  def updateItem = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    val name = request.body("name").toString.replaceAll("\"", "")
    itemsRepository.updateElement(index, name)
    Ok("Update indeksu " + index + " na wartość " + name)
  }

  def deleteItem = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    itemsRepository.removeFromList(index)
    Ok(index.toString)
  }

}