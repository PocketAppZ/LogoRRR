package app.logorrr.conf.mut

import app.logorrr.conf.{LogoRRRGlobals, StageSettings}
import app.logorrr.util.JfxUtils
import javafx.beans.property.{SimpleDoubleProperty, SimpleIntegerProperty}
import javafx.stage.Window

/**
 * App wide singleton to store and load global settings.
 */
object MutStageSettings {

  val windowListener = JfxUtils.onNew[Window](window => LogoRRRGlobals.bindWindow(window))

}


class MutStageSettings {

  val xProperty = new SimpleDoubleProperty()

  val yProperty = new SimpleDoubleProperty()

  val widthProperty = new SimpleIntegerProperty()

  val heightProperty = new SimpleIntegerProperty()

  def setX(x: Double): Unit = xProperty.set(x)

  def setY(y: Double): Unit = yProperty.set(y)

  def setWidth(width: Int): Unit = widthProperty.set(width)

  def getWidth(): Int = widthProperty.get()

  def setHeight(height: Int): Unit = heightProperty.set(height)

  def petrify(): StageSettings =
    StageSettings(xProperty.get()
      , yProperty.get()
      , widthProperty.get()
      , heightProperty.get())

}