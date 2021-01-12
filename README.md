# myList

Simple Scala API enabling users to manage a list of assets.

### Technologies used (or to be used)

* Scala 2.13.4
* Couchbase 6.0.2
* Kafka
* Akka HTTP
* ScalaTest

### Resources

* https://doc.akka.io/docs/akka-http/current/introduction.html
* https://github.com/cb372/sbt-explicit-dependencies
* http://www.scalatest.org/getting_started_with_feature_spec
* http://rest-assured.io/
* https://github.com/marcuslonnberg/sbt-docker
* https://doc.akka.io/docs/akka-http/current/routing-dsl/directives/index.html#composing-directives-with-operator
* https://doc.akka.io/docs/akka-http/current/routing-dsl/exception-handling.html
* https://github.com/Tapad/sbt-docker-compose

### To run all tests

At the moment the process is as follows:
* Run `./docker/initCouchbaseVanillaDocker.sh` to start and configure Couchbase in docker
* Run the Main class of MyList in IntelliJ
* `./sbt test`

I'm working on getting the instantiation of the DB etc running in a nice automated manner in Docker Compose instead as described in the section below.

### Docker Compose:

* This is still a work in progress, apps run, just need to figure out some connectivity issues preventing the tests from passing
* Build the mylist-couchbase-setup docker image `docker build . -t mylist-cb-setup`
* Run `./sbt dockerComposeUp` from the root of this project (`./sbt dockerComposeStop` to shut everything down)

### Useful SBT Commands

`./sbt`

I have included an sbt wrapper from [this repo on GitHub](https://github.com/paulp/sbt-extras). If on Linux/Mac you might need to run `chmod +x ./sbt` to ensure this is executable before running it.

`./sbt docker`

The [sbt-docker plugin](https://github.com/marcuslonnberg/sbt-docker) is being used to generate docker images of this application, running this will generate a local docker image, the config for this can be found in `build.sbt`.

`./sbt unusedCompileDependencies`

The [sbt-explicit-dependencies plugin](https://github.com/cb372/sbt-explicit-dependencies) has been added to this project to enable this command to highlight unused sbt dependencies.

`./sbt dockerComposeUp`

`./sbt dockerComposeStop`

`./sbt dockerComposeRestart`

The above are provided by the [DockerComposePlugin](https://github.com/Tapad/sbt-docker-compose)
