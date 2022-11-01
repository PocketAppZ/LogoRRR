package app.logorrr.model

import app.logorrr.util.CanLog
import app.logorrr.views.search.{Filter, Fltr}
import javafx.collections.{FXCollections, ObservableList}
import javafx.scene.paint.Color

import java.nio.file.Path
import java.time.Instant
import java.util.stream
import java.util.stream.Collectors

/** Abstraction for a log file */
object LogEntryFileReader extends CanLog {

  private def mkLogEntryList(parseColor: String => Color
                             , parseEntryForTimeInstant: String => Option[Instant])
                            (logFilePath: Path): ObservableList[LogEntry] = timeR({
    var lineNumber: Int = 0
    // trying to be very clever and use java stream instead of scala collections
    // it makes a notable difference in performance if we don't convert huge lists from java <-> scala
    val logEntryStream: stream.Stream[LogEntry] = LogFileReader.readFromFile(logFilePath).stream().map(l => {
      lineNumber = lineNumber + 1
      LogEntry(lineNumber, parseColor(l), l, parseEntryForTimeInstant(l))
    })
    FXCollections.observableList(logEntryStream.collect(Collectors.toList[LogEntry]()))
  }, s"Imported ${logFilePath.toAbsolutePath.toString} ... ")

  def from(logFilePath: Path, filters: Seq[Fltr], logEntryTimeFormat: LogEntryInstantFormat): ObservableList[LogEntry] = {
    mkLogEntryList(l => Filter.calcColor(l, filters), l => LogEntryInstantFormat.parseInstant(l, logEntryTimeFormat))(logFilePath)
  }

  def from(logFilePath: Path, filters: Seq[Fltr]): ObservableList[LogEntry] = mkLogEntryList(l => Filter.calcColor(l, filters), _ => None)(logFilePath)


}




