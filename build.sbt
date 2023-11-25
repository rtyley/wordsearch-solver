ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "wordsearch",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fansi" % "0.4.0",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    ),
    Test / testOptions +=
      Tests.Argument(TestFrameworks.ScalaTest, "-u", s"test-results/scala-${scalaVersion.value}", "-o")
  )
