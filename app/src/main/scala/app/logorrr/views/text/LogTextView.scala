package app.logorrr.views.text

import app.logorrr.conf.LogoRRRGlobals
import app.logorrr.conf.mut.MutLogFileSettings
import app.logorrr.model.LogEntry
import app.logorrr.util.{ClipBoardUtils, JfxUtils}
import javafx.collections.transformation.FilteredList
import javafx.scene.control._
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color

import scala.jdk.CollectionConverters._

object LogTextView {

  private val fixedCellSize = 26

  val timeBarColor = Color.BISQUE.darker()

  val timeBarOverflowColor = timeBarColor.darker()

}


class LogTextView(pathAsString: String
                  , filteredList: FilteredList[LogEntry]
                 ) extends BorderPane {
  /** 'pragmatic way' to determine width of max elems in this view */
  val maxLength = filteredList.size().toString.length

  private val mutLogFileSettings: MutLogFileSettings = LogoRRRGlobals.getLogFileSettings(pathAsString)

  val listView: ListView[LogEntry] = {
    val lv = new ListView[LogEntry]()
    lv.getStyleClass.add("dense")
    lv.setItems(filteredList)
    val i = mutLogFileSettings.selectedLineNumber.get()
    lv.getSelectionModel.select(i)
    lv
  }
  listView.setCellFactory((_: ListView[LogEntry]) => new LogEntryListCell())

  mutLogFileSettings.selectedLineNumber.addListener(JfxUtils.onNew((n: Number) => {
    Option(listView.getItems.filtered((t: LogEntry) => t.lineNumber == n.intValue()).get(0)) match {
      case Some(value) =>
        val relativeIndex = listView.getItems.indexOf(value)
        listView.getSelectionModel.select(relativeIndex)
        listView.scrollTo(relativeIndex - ((listView.getHeight / LogTextView.fixedCellSize) / 2).toInt)
      case None =>
    }

  }))

  setCenter(listView)

  /** scroll to end of listview */
  def scrollToEnd(): Unit = {
    listView.scrollTo(listView.getItems.size)
  }

  class LogEntryListCell extends ListCell[LogEntry] {
    styleProperty().bind(mutLogFileSettings.fontStyleBinding)
    setGraphic(null)
    val cm = new ContextMenu()
    val copyCurrentToClipboard = new MenuItem("copy text to clipboard")

    cm.getItems.add(copyCurrentToClipboard)

    override def updateItem(t: LogEntry, b: Boolean): Unit = {
      super.updateItem(t, b)
      Option(t) match {
        case Some(e) =>
          calculateLabel(e)
        case None =>
          setGraphic(null)
          setText(null)
          setContextMenu(null)
      }
    }

    private def calculateLabel(e: LogEntry): Unit = {
      setText(null)

      val entry = LogTextViewLabel(e
        , maxLength
        , mutLogFileSettings.filtersProperty.get().asScala.toSeq
        , mutLogFileSettings.fontStyleBinding)
      setGraphic(entry)
      copyCurrentToClipboard.setOnAction(_ => ClipBoardUtils.copyToClipboardText(e.value))
      setContextMenu(cm)
    }
  }
}


