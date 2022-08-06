package app.logorrr.views.search

import app.logorrr.util.LogoRRRFonts
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.{Label, ToggleButton}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.paint.Color

class SearchActivateRegexToggleButton
  extends ToggleButton {
  private val label = new Label("r")
  label.setStyle(LogoRRRFonts.jetBrainsMono(8))
  label.setTextFill(Color.DARKGREY)
  setGraphic(label)

}
