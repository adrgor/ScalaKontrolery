package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import repositiories.CategoriesRepository

import javax.inject.Inject

class CategoriesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val categoriesRepository = new CategoriesRepository

  def getAllCategories = Action {
    Ok(categoriesRepository.getCategoryList.toString())
  }

  def getCategoryById(id: Int) = Action {
    Ok(categoriesRepository.getCategoryById(id).toString())
  }

  def createNewCategory = Action(parse.json) { implicit request =>
    val name = request.body("name").toString.replaceAll("\"", "")
    categoriesRepository.addToCategoryList(name)
    Ok(name)
  }

  def updateCategory = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    val name = request.body("name").toString.replaceAll("\"", "")
    categoriesRepository.updateElement(index, name)
    Ok(index.toString)
  }

  def deleteCategory = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    categoriesRepository.removeFromList(index)
    Ok(index.toString)
  }
}
