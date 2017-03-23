name := "cerebro"

version := "0.6.3"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play"                    % "2.5.10",
  "com.typesafe.play" %% "play-ws"                 % "2.5.10",
  "com.typesafe.play" %% "play-slick"              % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions"   % "2.0.2",
  "org.xerial"        %  "sqlite-jdbc"             % "3.16.1",
  "org.specs2"        %% "specs2-junit"  % "3.8.4" % "test",
  "org.specs2"        %% "specs2-core"   % "3.8.4" % "test",
  "org.specs2"        %% "specs2-mock"   % "3.8.4" % "test"
)

lazy val root = (project in file(".")).
  enablePlugins(PlayScala, BuildInfoPlugin, LauncherJarPlugin).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "models"
  )

doc in Compile <<= target.map(_ / "none")

enablePlugins(JavaServerAppPackaging)
enablePlugins(SystemdPlugin)

pipelineStages := Seq(digest, gzip)

serverLoading := Some(ServerLoader.Systemd)
systemdSuccessExitStatus in Debian += "143"
systemdSuccessExitStatus in Rpm += "143"
linuxPackageMappings += packageTemplateMapping(s"/var/lib/${packageName.value}")() withUser((daemonUser in Linux).value) withGroup((daemonGroup in Linux).value)

rpmVendor:= "cerebro"

packageName in Linux := s"cerebro"
maintainer in Linux := "Alberto Paro <alberto.paro@gmail.com>"
packageSummary in Linux := "Cerebro Server"
packageDescription in Linux := "Cerebro Server"
bashScriptConfigLocation := Some(s"/etc/cerebro/jvmopts")
bashScriptExtraDefines += s"""addJava "-Dconfig.file=/etc/cerebro/application.conf" """
bashScriptExtraDefines += s"""addJava "-Dlogback.configurationFile=/etc/cerebro/logback.xml" """
rpmGroup := Some("Servers/Microservices")
//rpmDescription := "Cerebro Server"
rpmUrl := Some(s"https://github.com/lmenezes/cerebro")
rpmLicense := Some("Apache")
