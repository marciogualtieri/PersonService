package dal

import models.Person
import org.joda.time.DateTime

import scala.concurrent.Future

trait PersonRepository {

  def insertPerson(name: String, age: Int, lastUpdate: DateTime): Future[Person]

  def retrievePeople(): Future[Seq[Person]]

}
