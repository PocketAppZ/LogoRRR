package app.logorrr

import app.logorrr.conf.Settings
import app.logorrr.util.CanLog
import app.logorrr.views.main.LogoRRRStage
import javafx.stage.Stage

object LogoRRRApp {

  def main(args: Array[String]): Unit = {
    //LogUtil.init()
    javafx.application.Application.launch(classOf[LogoRRRApp], args: _*)
  }

}


class LogoRRRApp extends javafx.application.Application with CanLog {

  /**
   * will be called by the java bootstrapper
   */
  def start(stage: Stage): Unit = {
    Settings.someSettings match {
      case Some(settings) => LogoRRRStage(stage, settings, getHostServices).show()
      case None => logError("Could not initialize LogoRRR, quitting application.")
    }
  }

}