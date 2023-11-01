package app.logorrr.views.main

import app.logorrr.conf.LogoRRRGlobals
import app.logorrr.model.LogFileSettings
import app.logorrr.util.CanLog
import app.logorrr.views.logfiletab.LogFileTab
import javafx.scene.input.{DragEvent, TransferMode}
import javafx.scene.layout.BorderPane

import java.nio.file.{Files, Path}
import scala.jdk.CollectionConverters.ListHasAsScala


/**
 * Main UI element, all other gui elements are in some way children of this Borderpane
 */
class LogoRRRMainBorderPane extends BorderPane with CanLog {


  val logViewTabPane = new LogoRRRMainTabPane()
  setCenter(logViewTabPane)

  def init(): Unit = {
    /** needed to activate drag'n drop */
    setOnDragOver((event: DragEvent) => {
      if (event.getDragboard.hasFiles) {
        event.acceptTransferModes(TransferMode.ANY: _*)
      }
    })

    /** try to interpret dropped element as log file, activate view */
    setOnDragDropped((event: DragEvent) => {
      for (f <- event.getDragboard.getFiles.asScala) {
        val path = f.toPath
        if (Files.isDirectory(path)) {
          Files.list(path).filter((p: Path) => Files.isRegularFile(p)).forEach((t: Path) => dropLogFile(t))
        } else dropLogFile(path)
      }
    })

  }


  private def dropLogFile(path: Path): Unit = {
    val pathAsString = path.toAbsolutePath.toString

    if (Files.exists(path)) {
      if (!contains(pathAsString)) {
        addLogFile(path)
      } else {
        logTrace(s"$pathAsString is already opened, selecting tab ...")
        selectLog(pathAsString)
      }
    } else {
      logWarn(s"$pathAsString does not exist.")
    }
  }

  def addLogFile(path: Path): Unit = {
    val logFileSettings = LogFileSettings(path)
    LogoRRRGlobals.updateLogFile(logFileSettings)
    val tab = new LogFileTab(logFileSettings.pathAsString, logFileSettings.readEntries())
    addLogFileTab(tab)
    tab.init()
    selectLog(path.toAbsolutePath.toString)
  }

  def shutdown(): Unit = logViewTabPane.shutdown()

  /** Adds a new logfile to display */
  def addLogFileTab(tab: LogFileTab): Unit = logViewTabPane.add(tab)

  def selectLog(path: String): Unit = logViewTabPane.selectLog(path)

  /** select log file which is last in tabPane */
  def selectLastLogFile(): Unit = logViewTabPane.selectLastLogFile()

  def contains(path: String): Boolean = logViewTabPane.contains(path)


}



