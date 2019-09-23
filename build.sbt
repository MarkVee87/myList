name := "myList"

version := "0.1"

scalaVersion := "2.12.10"

lazy val macwireVersion = "2.3.1"
lazy val loggingVersion = "3.9.0"
lazy val akkaHttpVersion = "10.1.10"
lazy val akkaStreamVersion = "2.5.25"
lazy val scalaTestVersion = "3.0.8"
lazy val restAssuredVersion = "4.1.1"
lazy val slf4jVersion = "1.7.28"
lazy val typesafeConfigVersion = "1.3.3"

libraryDependencies ++= configDependencies
libraryDependencies ++= akkaDependencies
libraryDependencies ++= loggingDependencies
libraryDependencies ++= testingDependencies

val configDependencies = Seq(
  "com.softwaremill.macwire" %% "macros" % macwireVersion,
  "com.typesafe" % "config" % typesafeConfigVersion
)

val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaStreamVersion
)

val loggingDependencies = Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % loggingVersion,
  "org.slf4j" % "slf4j-simple" % slf4jVersion
)

val testingDependencies = Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "io.rest-assured" % "rest-assured" % restAssuredVersion % Test
)