package controllers

import play.api.mvc._
import play.api.i18n._
import play.api.libs.json.{ JsValue, Json, Writes }
import models._
import dal._

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject._

class PersonController @Inject() (repo: PersonRepository, val messagesApi: MessagesApi)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.index("Crawler Scheduler Service"))
  }

  implicit val personWrites = new Writes[Person] {
    def writes(person: Person) = Json.obj(
      "id" -> person.id,
      "name" -> person.name,
      "age" -> person.age
    )
  }

  def returnPeople(): Action[AnyContent] = Action.async {
    repo.retrievePeople().map { people =>
      Ok(Json.toJson(people))
    }
  }

  def createPerson(): Action[JsValue] = Action.async(parse.json) {
    implicit request =>
      {
        {
          for {
            id <- (request.body \ "id").asOpt[Long]
            name <- (request.body \ "name").asOpt[String]
            age <- (request.body \ "age").asOpt[Int]
          } yield repo.insertPerson(name, age).map { person => Ok("Id of Person Added : " + person.id) }
        }.getOrElse(Future { BadRequest("Wrong json format") })
      }
  }

}
