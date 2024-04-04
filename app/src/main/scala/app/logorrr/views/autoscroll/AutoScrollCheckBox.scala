package app.logorrr.views.autoscroll

import app.logorrr.conf.LogoRRRGlobals
import app.logorrr.io.FileId
import app.logorrr.model.HasFileId
import app.logorrr.views.{UiNode, UiNodeAware}
import javafx.scene.control.{CheckBox, Tooltip}

object AutoScrollCheckBox extends UiNodeAware {

  override def uiNode(id: FileId): UiNode = UiNode("autoscrollcheckbox-" + id)
}

class AutoScrollCheckBox(val fileId: FileId) extends CheckBox with HasFileId {
  setId(AutoScrollCheckBox.uiNode(fileId).ref)
  setTooltip(new Tooltip("autoscroll"))
  selectedProperty().bindBidirectional(LogoRRRGlobals.getLogFileSettings(fileId).autoScrollActiveProperty)


}
