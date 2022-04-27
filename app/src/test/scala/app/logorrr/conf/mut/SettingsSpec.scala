package app.logorrr.conf.mut

import app.logorrr.conf.Settings
import org.scalacheck.Gen

object SettingsSpec {

  val gen: Gen[Settings] = for {
    stageSettings <- StageSettingsSpec.gen
    blockSettings <- BlockSettingsSpec.gen
    recentFileSettings <- RecentFileSettingsSpec.gen
  } yield Settings(stageSettings, recentFileSettings)
}
