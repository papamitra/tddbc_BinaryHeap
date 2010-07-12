import sbt._
class SpecsProject(info: ProjectInfo) extends DefaultProject(info) {
//  val specsRepo = "Specs Repository" at "http://specs.googlecode.com/svn/maven2/"

//  val specs = "org.scala-tools.testing" % "specs" % "1.6.2.1"
//  val scalatest = "org.scalatest" % "scalatest" % "1.0" % "test"
  val scalaToolsSnapshots = "scala-tools.org Snapshots" at "http://scala-tools.org/repo-snapshots"
  val scalatest = "org.scalatest" % "scalatest" % "1.2-for-scala-2.8.0.RC5-SNAPSHOT" % "test"

//  val scalacheck = "org.scala-tools.testing" % "scalacheck" % "1.5"
//  val mockito = "org.mockito" % "mockito-core" % "1.7"
//  val junit = "junit" % "junit" % "4.4"
}
