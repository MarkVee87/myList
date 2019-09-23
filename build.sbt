name := "myList"

version := "0.1"

scalaVersion := "2.12.10"

lazy val macwireVersion = "2.3.1"
lazy val loggingVersion = "3.9.0"
lazy val akkaHttpVersion = "10.1.10"
lazy val akkaStreamVersion = "2.5.25"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % loggingVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
  "com.softwaremill.macwire" %% "macros" % macwireVersion,
  "com.softwaremill.macwire" %% "macrosakka" % macwireVersion,
  "com.softwaremill.macwire" %% "util" % macwireVersion,
  "com.softwaremill.macwire" %% "proxy" % macwireVersion
)