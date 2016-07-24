import java.util.concurrent.Callable

import play.api.test._
import play.api.test.Helpers._
import java.util.concurrent.TimeUnit._

import helpers.PersonTestHelper
import models.Person
import play.api.mvc.Result

import scala.concurrent.Future

class PersonControllerSpec extends PersonTestHelper {

  val BoJackHorseman = Person(1, "BoJack Horseman", 52)
  val MrPeanutbutter = Person(2, "Mr. Peanutbutter", 47)

  "PersonController" should {

    "create a person" in {
      val getResponse: Future[Result] = postPerson(BoJackHorseman)
      status(getResponse) mustBe OK
      contentType(getResponse) mustBe Some("text/plain")
      contentAsString(getResponse) mustBe "Id of Person Added : 1"
    }

    "retrieve persons" in {
      postPerson(BoJackHorseman)
      postPerson(MrPeanutbutter)
      val postResponse: Future[Result] = getPersons
      status(postResponse) mustBe OK
      contentType(postResponse) mustBe Some("application/json")
      persons(postResponse) mustBe Seq(BoJackHorseman, MrPeanutbutter)
    }

  }

}
