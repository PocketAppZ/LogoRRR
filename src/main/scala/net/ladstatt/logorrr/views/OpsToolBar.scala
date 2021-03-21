package net.ladstatt.logorrr.views

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control._
import net.ladstatt.logorrr.CaseInsensitiveFilter


class OpsToolBar(logView: LogView) extends ToolBar {

  val initialText = "<enter search string>"
  val searchTextField = new TextField()
  searchTextField.setPrefWidth(500)
  searchTextField.setPromptText(initialText)

  private val label = new Label("new filter")
  label.setPrefWidth(100)
  val cp = new ColorPicker()
  val add = new Button("add")
  add.setOnAction(new EventHandler[ActionEvent]() {
    override def handle(t: ActionEvent): Unit = {
      logView.addFilter(CaseInsensitiveFilter(searchTextField.getText, cp.getValue))
      searchTextField.setText("")
    }
  })
  getItems.addAll(label, searchTextField, cp, add)
}