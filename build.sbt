ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2022",
    idePackagePrefix := Some("com.aronstrandberg")
  )

libraryDependencies += "org.parboiled" %% "parboiled" % "2.4.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.14" % "test"