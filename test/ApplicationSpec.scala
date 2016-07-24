import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.scalatestplus.play.{ OneAppPerTest, PlaySpec }
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Application" should {

    "render the index page" in new WithApplication {
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }
  }

  "Routes" should {

    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/a/page/that/does/not/exist")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }
}
