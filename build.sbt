name := """bitcoin-tracker-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.12.3"

resolvers ++= Seq(
  "applctv-bintray" at "https://dl.bintray.com/applctv/gcp-scala-datastore/"
)

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test,
  ws,
  ehcache,
  "com.github.tototoshi" %% "play-json-naming" % "1.2.0",
  "com.h2database" % "h2" % "1.4.192",
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
  "com.google.cloud" % "google-cloud-datastore" % "1.6.0",
  "com.googlecode.objectify" % "objectify" % "5.1.21",
  "io.applicative" %% "datastore-scala-wrapper" % "1.0-rc8"
)
