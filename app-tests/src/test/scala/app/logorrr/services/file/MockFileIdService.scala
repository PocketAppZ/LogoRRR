package app.logorrr.services.file

import app.logorrr.io.FileId

/**
 * Given a list of files, it returns each file in order, and if the last file is reached, a
 * `None`
 *
 * @param files which this service is returning
 */
class MockFileIdService(files: Seq[FileId]) extends FileIdService {

  private val it = files.iterator

  override def provideFileId: Option[FileId] = {
    if (it.hasNext) {
      Option(it.next())
    } else None
  }

}
