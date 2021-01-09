import Dependencies._

name := "mylist"

organization := "mveeprojects"

version := "0.1"

scalaVersion := "2.13.4"

test in assembly := {}

dockerfile in docker := {
  buildOptions in docker := BuildOptions(cache = false)
  val artifact: File       = assembly.value
  val artifactTargetPath   = s"/app/"
  val artifactName: String = artifact.getName
  new Dockerfile {
    from("openjdk:8-jre")
    runRaw(s"mkdir -p $artifactTargetPath")
    copy(artifact, s"$artifactTargetPath/$artifactName")
    workDir(artifactTargetPath)
    expose(9099)
    entryPoint("java", "-jar", artifactName)
  }
}

imageNames in docker := Seq(
  ImageName(s"${organization.value}/${name.value}:latest"),
  ImageName(
    namespace = Some(organization.value),
    repository = name.value,
    tag = Some("v" + version.value)
  )
)

libraryDependencies ++= (
  configDependencies ++
    akkaDependencies ++
    loggingDependencies ++
    testingDependencies ++
    couchbaseDependencies
)

enablePlugins(JavaAppPackaging, DockerComposePlugin)

dockerImageCreationTask := (publishLocal in Docker).value
