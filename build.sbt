val dottyVersion = "0.25.0-RC2"

lazy val tinkoffVersion = "0.4.1"
lazy val akkaVersion = "2.6.4"
lazy val akkaVersionHttp = "10.1.11"
lazy val softwaremillVersion = "2.3.3"
lazy val catsVersion = "2.2.0-RC1"
lazy val fs2Version = "2.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += ("ru.tinkoff.invest" % "openapi-java-sdk" % tinkoffVersion pomOnly()),
    libraryDependencies += ("ru.tinkoff.invest" % "openapi-java-sdk-example" % tinkoffVersion),
    libraryDependencies += ("ru.tinkoff.invest" % "openapi-java-sdk-core" % tinkoffVersion),
    libraryDependencies += ("com.typesafe.akka" %% "akka-actor" % akkaVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-stream" % akkaVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-http" % akkaVersionHttp).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-http-testkit" % akkaVersionHttp % Test).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-persistence" % akkaVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-distributed-data" % akkaVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.typesafe.akka" %% "akka-slf4j" % akkaVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("ch.qos.logback" % "logback-classic" % "1.2.3").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("ch.qos.logback" % "logback-core" % "1.2.3").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("net.logstash.logback" % "logstash-logback-encoder" % "6.2").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("org.typelevel" %% "cats-effect" % "2.0.0").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("org.typelevel" %% "cats-core" % "2.0.0").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("co.fs2" %% "fs2-core" % "2.0.1").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("co.fs2" %% "fs2-io" % "2.0.1").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.softwaremill.macwire" %% "macrosakka" % softwaremillVersion % "provided").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.softwaremill.macwire" %% "macros" % softwaremillVersion % "provided").withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.softwaremill.macwire" %% "proxy" % softwaremillVersion).withDottyCompat(scalaVersion.value),
    libraryDependencies += ("com.softwaremill.macwire" %% "util" % softwaremillVersion).withDottyCompat(scalaVersion.value),
  )
