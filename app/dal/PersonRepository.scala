package dal

import models.Person

import scala.concurrent.Future

trait PersonRepository {

  def insertPerson(name: String, age: Int): Future[Person]

  def retrievePeople(): Future[Seq[Person]]

}
