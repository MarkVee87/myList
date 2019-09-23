# myList

Simple Scala API enabling users to manage a list of assets.

### Technologies used (or to be used)
* Scala 2.12.10
* Couchbase 6.0.2
* Kafka
* Akka HTTP
* ScalaTest

### Resources
* https://doc.akka.io/docs/akka-http/current/introduction.html
* https://github.com/cb372/sbt-explicit-dependencies
* http://www.scalatest.org/getting_started_with_feature_spec
* http://rest-assured.io/

### Useful SBT Commands

`sbt unusedCompileDependencies`

The [`sbt-explicit-dependencies` plugin](https://github.com/cb372/sbt-explicit-dependencies) has been added to this project to enable this command to highlight unused sbt dependencies.