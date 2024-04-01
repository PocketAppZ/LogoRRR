package app.logorrr

import app.logorrr.io.FileId
import app.logorrr.views.LogoRRRNodes
import app.logorrr.views.logfiletab.LogFileTab
import org.junit.jupiter.api.Test

/**
 * Checks if LogoRRR can open a file via the 'open file' menu
 */
class OpenFileTest extends SingleFileApplicationTest(TestFiles.simpleLog0) {

  /**
   * checks if an open file creates a new logfiletab with an id matching the file opened.
   */
  @Test def openFileTest(): Unit = {
    waitForVisibility(LogoRRRNodes.FileMenu)
    clickOn(LogoRRRNodes.FileMenu)
    waitForVisibility(LogoRRRNodes.FileMenuOpenFile)
    clickOn(LogoRRRNodes.FileMenuOpenFile)
    waitForVisibility(LogFileTab.idFor(FileId(path)))
  }

}
