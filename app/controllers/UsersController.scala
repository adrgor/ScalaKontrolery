package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import repositiories.UsersRepository

import javax.inject.Inject

class UsersController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  val usersRepository = new UsersRepository

  def getAllUsers = Action {
    Ok(usersRepository.getUserList.toString())
  }

  def getUserById(id: Int) = Action {
    Ok(usersRepository.getUserById(id).toString())
  }

  def createNewUser = Action(parse.json) { implicit request =>
    val name = request.body("name").toString.replaceAll("\"", "")
    usersRepository.addToUserList(name)
    Ok(name)
  }

  def updateUser = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    val name = request.body("name").toString.replaceAll("\"", "")
    usersRepository.updateElement(index, name)
    Ok("Update indeksu " + index + " na wartość " + name)
  }

  def deleteUser = Action(parse.json) { implicit request =>
    val index = request.body("index").toString.replaceAll("\"", "").toInt
    usersRepository.removeFromList(index)
    Ok(index.toString)
  }
}
