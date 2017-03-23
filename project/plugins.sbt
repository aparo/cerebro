// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M8")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.6.1")

// if you use JDeb
libraryDependencies += "org.vafer" % "jdeb" % "1.3" artifacts (Artifact("jdeb", "jar", "jar"))

// if you use Spotify Docker
libraryDependencies += "com.spotify" % "docker-client" % "3.2.1"
