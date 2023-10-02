package app.logorrr.conf.mut

import app.logorrr.conf.BlockSettings
import app.logorrr.model.{LogEntryInstantFormat, LogFileSettings}
import app.logorrr.util.LogoRRRFonts
import app.logorrr.views.search.Filter
import javafx.beans.binding.{BooleanBinding, StringBinding}
import javafx.beans.property._
import javafx.collections.FXCollections

import scala.jdk.CollectionConverters._

object MutLogFileSettings {

  def apply(logFileSettings: LogFileSettings): MutLogFileSettings = {
    val s = new MutLogFileSettings
    s.selectedLineNumber(logFileSettings.selectedLineNumber)
    s.setFontSize(logFileSettings.fontSize)
    s.setBlockSettings(logFileSettings.blockSettings)
    s.setPathAsString(logFileSettings.pathAsString)
    s.firstOpenedProperty.set(logFileSettings.firstOpened)
    s.setDividerPosition(logFileSettings.dividerPosition)
    s.setFilters(logFileSettings.filters)
    s.someLogEntrySettingsProperty.set(logFileSettings.someLogEntryInstantFormat)
    s.setAutoScroll(logFileSettings.autoScroll)
    s
  }
}

class MutLogFileSettings {

  private val pathAsStringProperty = new SimpleStringProperty()
  private val firstOpenedProperty = new SimpleLongProperty()
  val selectedLineNumber = new SimpleIntegerProperty()
  private val dividerPositionProperty = new SimpleDoubleProperty()
  private val fontSizeProperty = new SimpleIntegerProperty()

  val autoScrollProperty = new SimpleBooleanProperty()
  val filtersProperty = new SimpleListProperty[Filter](FXCollections.observableArrayList())
  val someLogEntrySettingsProperty = new SimpleObjectProperty[Option[LogEntryInstantFormat]](None)
  val blockWidthSettingsProperty = new SimpleIntegerProperty()

  def setFilters(filters: Seq[Filter]): Unit = {
    filtersProperty.setAll(filters.asJava)
  }

  def setFilter(index: Int, filter: Filter): Unit = {
    filtersProperty.set(index, filter)
  }

  val hasLogEntrySettingBinding: BooleanBinding = new BooleanBinding {
    bind(someLogEntrySettingsProperty)

    override def computeValue(): Boolean = {
      Option(someLogEntrySettingsProperty.get()).exists(_.isDefined)
    }
  }

  val fontStyleBinding: StringBinding = new StringBinding {
    bind(fontSizeProperty)

    override def computeValue(): String = LogoRRRFonts.jetBrainsMono(fontSizeProperty.get())
  }

  def setLogEntryInstantFormat(lef: LogEntryInstantFormat): Unit = {
    someLogEntrySettingsProperty.set(Option(lef))
  }

  def setAutoScroll(autoScroll: Boolean): Unit = autoScrollProperty.set(autoScroll)

  def isAutoScroll: Boolean = autoScrollProperty.get()

  def getFontSize: Int = fontSizeProperty.get()

  def getFilters: Seq[Filter] = filtersProperty.asScala.toSeq

  def setBlockSettings(bs: BlockSettings): Unit = blockWidthSettingsProperty.set(bs.width)

  def setPathAsString(path: String): Unit = pathAsStringProperty.set(path)

  def getPathAsString(): String = pathAsStringProperty.get()

  def selectedLineNumber(lineNumber: Int): Unit = selectedLineNumber.set(lineNumber)

  def setFontSize(fontSize: Int): Unit = fontSizeProperty.set(fontSize)

  def setDividerPosition(dividerPosition: Double): Unit = dividerPositionProperty.set(dividerPosition)

  def getDividerPosition(): Double = dividerPositionProperty.get()

  def getFirstOpened(): Long = firstOpenedProperty.get()

  def petrify(): LogFileSettings = {
    val lfs =
      LogFileSettings(pathAsStringProperty.get()
        , selectedLineNumber.get()
        , firstOpenedProperty.get()
        , dividerPositionProperty.get()
        , fontSizeProperty.get()
        , filtersProperty.get().asScala.toSeq
        , BlockSettings(blockWidthSettingsProperty.get())
        , someLogEntrySettingsProperty.get()
        , autoScrollProperty.get())
    lfs
  }
}

