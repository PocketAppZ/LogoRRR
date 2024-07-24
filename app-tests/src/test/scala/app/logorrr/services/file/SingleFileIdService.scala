package app.logorrr.services.file

import app.logorrr.io.FileId

/**
 * Service which always opens given file
 *
 * @param fileId file reference to open
 */
class SingleFileIdService(fileId: FileId) extends MockFileIdService(Seq(fileId))
