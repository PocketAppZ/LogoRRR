package app.logorrr.views.about

import app.logorrr.meta.AppMeta
import app.logorrr.util.{HLink, ImageCp, LogoRRRFonts, PropsCp}
import app.logorrr.views.UiNodes
import javafx.geometry.{Insets, Pos}
import javafx.scene.control._
import javafx.scene.image.ImageView
import javafx.scene.layout.{BorderPane, HBox, VBox}

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId}
import java.util.Properties


object AboutScreen {

  val logo: ImageCp = ImageCp("/app/logorrr/icon/logorrr-icon-128.png", 128, 128)

  lazy val links: Seq[HLink] = Seq(
    HLink(UiNodes.AboutDialogOpenLogorrrMainSite, "https://www.logorrr.app/", "https://www.logorrr.app/")
    , HLink(UiNodes.AboutDialogOpenDevelopmentBlog, "https://www.logorrr.app/posts/index.html", "Development blog")
    , HLink(UiNodes.AboutDialogOpenIssuePage, "https://github.com/rladstaetter/LogoRRR/issues/", "Request a feature or report a bug"))

  case class MonoLabel(text: String, size: Int) extends Label(text) {
    setStyle(LogoRRRFonts.jetBrainsMono(size))
  }

  class HLinkView(links: Seq[HLink]) extends VBox {
    setAlignment(Pos.CENTER_LEFT)
    setSpacing(10)
    setPrefWidth(400)
    setPadding(new Insets(30, 20, 20, 20))

    links.foreach(l => getChildren.add(l.mkHyperLink()))

  }

}


object BuildProps {

  lazy val Instance = new BuildProps
}

class BuildProps {

  lazy val buildProps: Properties = PropsCp("/build.properties").asProperties(getClass)

  lazy val githash: String = buildProps.getProperty("revision")

  lazy val timestamp: String = {
    val PATTERN_FORMAT = "dd.MM.yyyy"
    val formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
      .withZone(ZoneId.systemDefault());
    val i = Instant.ofEpochMilli(buildProps.getProperty("timestamp").toLong)
    formatter.format(i)
  }

}

class AboutScreen extends BorderPane {

  private def mkLogo(): ImageView = AboutScreen.logo.imageView()

  private def mkHeader(): Label = AboutScreen.MonoLabel(AppMeta.fullAppNameWithVersion, 50)

  setPadding(new Insets(10, 10, 10, 10))
  setTop(mkHeader())
  setLeft(mkLogo())
  setRight(new AboutScreen.HLinkView(AboutScreen.links))
  val hBox = new HBox()
  hBox.setAlignment(Pos.CENTER_LEFT)
  hBox.getChildren.add(new Label(BuildProps.Instance.timestamp + " " + BuildProps.Instance.githash))
  setBottom(hBox)

}
