package models

import org.joda.time.DateTime

case class Person(id: Long, name: String, age: Int, lastUpdate: DateTime)
