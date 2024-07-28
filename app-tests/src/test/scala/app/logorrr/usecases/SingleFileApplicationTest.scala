package app.logorrr.usecases

import app.logorrr.conf.Settings
import app.logorrr.io.FileId
import app.logorrr.services.LogoRRRServices
import app.logorrr.services.file.SingleFileIdService
import app.logorrr.services.hostservices.MockHostServices
import app.logorrr.steps.CanStartApplication

/**
 * Test which work with a single file can extend this test
 */
class SingleFileApplicationTest(val fileId: FileId)
  extends TestFxBaseApplicationTest
    with CanStartApplication {

  protected lazy val settings: Settings = Settings.Default

  final def services: LogoRRRServices = {
    LogoRRRServices(settings
      , new MockHostServices
      , new SingleFileIdService(fileId)
      , isUnderTest = true)
  }


}

