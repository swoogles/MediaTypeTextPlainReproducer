ThisBuild / scalaVersion := "2.12.20"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

val zioHttpVersion =
  "3.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "MediaTypeTextPlainReproducer",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.1",
      "dev.zio" %% "zio-test" % "2.1.1" % Test,
      "dev.zio" %% "zio-http" % zioHttpVersion,
      "dev.zio" %% "zio-schema" % "1.3.0",
      "dev.zio" %% "zio-schema-json" % "1.3.0"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
