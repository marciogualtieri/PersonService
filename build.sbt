name := """scheduler-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0"
  , "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
  , "com.h2database" % "h2" % "1.4.190"
  , "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
  , "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
  , "com.github.tototoshi" %% "slick-joda-mapper" % "2.2.0"
  , "joda-time" % "joda-time" % "2.7"
  , "org.joda" % "joda-convert" % "1.7"
  , "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

javaOptions in Test += "-Dconfig.file=test/conf/application.test.conf"
coverageEnabled := true
scalariformSettings
jacoco.settings

