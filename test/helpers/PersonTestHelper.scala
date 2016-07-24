package helpers

import models.Person
import org.scalatestplus.play.{ OneAppPerTest, PlaySpec }
import play.api.libs.json.{ JsPath, Json, Reads, Writes }
import play.api.mvc.Result
import play.api.test.{ FakeHeaders, FakeRequest }
import play.api.test.Helpers._
import play.api.libs.functional.syntax._

import scala.concurrent.Future

trait PersonTestHelper extends PlaySpec with OneAppPerTest {

  private val PostHeaders = FakeHeaders(Seq("Content-type" -> "application/json"))

  private implicit val PersonWrites = new Writes[Person] {
    def writes(person: Person) = Json.obj(
      "id" -> person.id,
      "name" -> person.name,
      "age" -> person.age
    )
  }

  private implicit val PersonReads: Reads[Person] = (
    (JsPath \ "id").read[Long] and
    (JsPath \ "name").read[String] and
    (JsPath \ "age").read[Int]
  )(Person.apply _)

  def getPeople: Future[Result] = {
    val postRequest = FakeRequest(GET, controllers.routes.PersonController.returnPeople().url)
    route(app, postRequest).get
  }

  def postPerson(person: Person): Future[Result] = {
    val json = Json.toJson(person)
    val request = FakeRequest(POST, controllers.routes.PersonController.createPerson().url, PostHeaders, json)
    route(app, request).get
  }

  def persons(response: Future[Result]): Seq[Person] = {
    Json.fromJson[Seq[Person]](Json.parse(contentAsString(response))).get
  }
}
