package app.logorrr.conf.mut

import app.logorrr.conf.{LogoRRRGlobals, SettingsIO, StageSettings}
import app.logorrr.util.JfxUtils
import javafx.beans.property.{SimpleDoubleProperty, SimpleIntegerProperty}
import javafx.stage.Window

/**
 * App wide singleton to store and load global settings.
 */
object MutStageSettings {

  val windowListener = JfxUtils.onNew[Window](window => LogoRRRGlobals.bindWindow(window))

  def apply(stageSettings: StageSettings): MutStageSettings = {
    val s = new MutStageSettings
    s.setX(stageSettings.x)
    s.setY(stageSettings.y)
    s.setWidth(stageSettings.width)
    s.setHeight(stageSettings.height)
    s
  }

}


class MutStageSettings extends Petrify[StageSettings] {

  val xProperty = new SimpleDoubleProperty()

  val yProperty = new SimpleDoubleProperty()

  val widthProperty = new SimpleIntegerProperty()

  val heightProperty = new SimpleIntegerProperty()

  def setX(x: Double): Unit = xProperty.set(x)

  def setY(y: Double): Unit = yProperty.set(y)

  def setWidth(width: Int): Unit = widthProperty.set(width)

  def setHeight(height: Int): Unit = heightProperty.set(height)

  override def petrify(): StageSettings =
    StageSettings(xProperty.get()
      , yProperty.get()
      , widthProperty.get()
      , heightProperty.get())

}