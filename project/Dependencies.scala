import sbt._

object Dependencies {

  import Versions._

  val configDependencies = Seq(
    "com.softwaremill.macwire" %% "macros"     % macwireVersion,
    "com.github.pureconfig"    %% "pureconfig" % pureconfigVersion,
    "com.typesafe"              % "config"     % typesafeConfigVersion
  )

  val akkaDependencies = Seq(
    "com.typesafe.akka" %% "akka-http"      % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"    % akkaStreamVersion,
    "com.typesafe.akka" %% "akka-actor"     % akkaStreamVersion
  )

  val loggingDependencies = Seq(
    "ch.qos.logback" % "logback-classic" % logbackVersion
  )

  val testingDependencies = Seq(
    "org.scalatest"  %% "scalatest"    % scalaTestVersion   % Test,
    "io.rest-assured" % "rest-assured" % restAssuredVersion % Test
  )

  val couchbaseDependencies = Seq(
    "com.couchbase.client" % "java-client" % couchbaseClientVersion,
    "net.liftweb"         %% "lift-json"   % liftJsonVersion
  )

  object Versions {
    val macwireVersion         = "2.3.7"
    val logbackVersion         = "1.2.3"
    val akkaHttpVersion        = "10.1.10"
    val akkaStreamVersion      = "2.5.25"
    val scalaTestVersion       = "3.0.8"
    val restAssuredVersion     = "4.1.1"
    val typesafeConfigVersion  = "1.3.3"
    val pureconfigVersion      = "0.14.0"
    val couchbaseClientVersion = "2.7.9"
    val liftJsonVersion        = "3.4.3"
  }
}
