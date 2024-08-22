package app.logorrr.views.settings.timestamp

import app.logorrr.conf.mut.MutLogFileSettings
import app.logorrr.model.LogEntry
import app.logorrr.util.JfxUtils
import app.logorrr.views.block.ChunkListView
import app.logorrr.views.ops.time.TimeOpsToolBar
import javafx.collections.ObservableList
import javafx.scene.Scene
import javafx.stage.{Modality, Stage, Window}

object TimestampSettingStage {

  val width = 1100
  val height = 300

}

class TimestampSettingStage(owner: Window
                            , settings: MutLogFileSettings
                            , chunkListView: ChunkListView
                            , logEntries: ObservableList[LogEntry]
                            , timeOpsToolBar: TimeOpsToolBar) extends Stage {

  initOwner(owner)
  initModality(Modality.APPLICATION_MODAL)
  setTitle(s"Specify the timestamp range (from - to columns) and the time pattern for ${settings.getFileId.fileName}")

  // center relative to owner window
  /*
  setOnShowing(_ => {
    val x = owner.getX + (owner.getWidth - getWidth) / 2
    val y = owner.getY + (owner.getHeight - getHeight) / 2
    setX(x)
    setY(y)
  })
  */

  val scene = new Scene(
    new TimestampSettingsBorderPane(settings
      , logEntries
      , chunkListView
      , timeOpsToolBar
      , JfxUtils.closeStage(this))
    , TimestampSettingStage.width
    , TimestampSettingStage.height)
  setScene(scene)
  setOnCloseRequest(_ => this.close())
}