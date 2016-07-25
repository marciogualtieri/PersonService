package dal

import javax.inject.{ Inject, Singleton }

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import models.Person
import org.joda.time.DateTime

import scala.concurrent.{ ExecutionContext, Future }

import com.github.tototoshi.slick.GenericJodaSupport

/**
 * Slick implementation for a person repository.
 *
 * @param dbConfigProvider The Play db config provider. Play will inject this for you.
 */
@Singleton
class PersonSlickRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends PersonRepository {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import driver.api._

  object PortableJodaSupport extends GenericJodaSupport(dbConfig.driver)
  import PortableJodaSupport._

  private class PeopleTable(tag: Tag) extends Table[Person](tag, "people") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def age = column[Int]("age")

    def lastUpdate = column[DateTime]("last_update")

    def * = (id, name, age, lastUpdate) <> ((Person.apply _).tupled, Person.unapply)
  }

  private val people = TableQuery[PeopleTable]

  def insertPerson(name: String, age: Int, lastUpdate: DateTime): Future[Person] = db.run {
    (people.map(p => (p.name, p.age, p.lastUpdate))
      returning people.map(_.id)
      into ((record, id) => Person(id, record._1, record._2, record._3))) += (name, age, lastUpdate)
  }

  def retrievePeople(): Future[Seq[Person]] = db.run {
    people.result
  }
}
