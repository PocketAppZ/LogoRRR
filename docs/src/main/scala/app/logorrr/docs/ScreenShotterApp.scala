package app.logorrr.docs

import app.logorrr.LogoRRRAppBuilder
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Node
import javafx.stage.Stage

import java.nio.file.{Files, Path, Paths}
import javax.imageio.ImageIO

/**
 * Simple application to take screenshots from a JavaFX app for documentation purposes
 */
object ScreenShotterApp {

  def persistNodeState(node: Node, target: Path) = {
    Thread.sleep(500)
    val renderedNode = node.snapshot(null, null)
    ImageIO.write(SwingFXUtils.fromFXImage(renderedNode, null), "png", target.toFile)
  }

  def main(args: Array[String]): Unit = {
    javafx.application.Application.launch(classOf[ScreenShotterApp], args: _*)
  }
}

class ScreenShotterApp extends javafx.application.Application {

  def start(stage: Stage): Unit = {
    for (Area(width, height) <- Area.seq) {
      val s = LogoRRRAppBuilder.withStage(stage, Seq("logfiles/logic.2.log"), width, height)
      val bPath = Paths.get("docs/releases/21.4.0/")
      Files.createDirectories(bPath)
      val f = bPath.resolve(s"${width}x$height.png")
      ScreenShotterApp.persistNodeState(s.getScene.getRoot, f)
      println("created " + f.toAbsolutePath.toString)
      s.show()
    }

  }


}