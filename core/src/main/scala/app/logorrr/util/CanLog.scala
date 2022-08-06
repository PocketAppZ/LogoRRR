package app.logorrr.util

trait CanLog {

  // ironic that this application doesn't use proper logging currently ;-)
  // afair there was an issue with graalvm not to use java.util.logging which I
  // couldn't yet resolve.
  def logInfo(s: String): Unit = System.out.println(s"INFO: $s")

  def logTrace(s: String): Unit = System.out.println(s"FINEST: $s")

  def logError(s: String): Unit = System.err.println(s"ERROR: $s")

  def logException(msg: String, t: Throwable): Unit = {
    logError(msg)
    for (t <- t.getStackTrace) {
      logError(t.toString)
    }
  }

  def logWarn(s: String): Unit = System.out.println("WARN: " + s)

  def timeR[T](a: => T, s: String): T = {
    val before = System.currentTimeMillis()
    val r = a
    val after = System.currentTimeMillis()
    logTrace(s"$s (duration: ${after - before} millis)")
    r
  }
}
