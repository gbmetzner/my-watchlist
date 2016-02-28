import sbt._
import Keys._
import PlayKeys._

name := """my-watchlist"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0" withSources(),
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" withSources(),
  "org.scalatest" %% "scalatest" % "2.2.6" % Test withSources()
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// run gulp
playRunHooks += RunSubProcess("gulp")