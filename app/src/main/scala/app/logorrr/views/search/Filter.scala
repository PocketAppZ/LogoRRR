package app.logorrr.views.search

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.paint.Color
import pureconfig.generic.semiauto.{deriveReader, deriveWriter}
import pureconfig.{ConfigReader, ConfigWriter}

object Filter {

  // Filter has javafx Color in it's constructor, we need to define explicit mappings
  implicit lazy val colorReader: ConfigReader[Color] = ConfigReader[String].map(s => Color.web(s))
  implicit lazy val colorWriter: ConfigWriter[Color] = ConfigWriter[String].contramap(c => c.toString)

  implicit lazy val reader: ConfigReader[Filter] = deriveReader[Filter]
  implicit lazy val writer: ConfigWriter[Filter] = deriveWriter[Filter]


  val unClassifiedFilterColor: Color = Color.LIGHTGREY

  /**
   * calculate a color for this log entry.
   *
   * - either white if no search filter hits
   * - given color if only one hit
   * - a melange of all colors from all hits in all other cases
   * */
  def calcColor(value: String, filters: Seq[Fltr]): Color = {
    val hits = filters.filter(sf => sf.matches(value))
    val color = {
      if (hits.isEmpty) {
        Color.LIGHTGREY
      } else if (hits.size == 1) {
        hits.head.color
      } else {
        val c = hits.tail.foldLeft(hits.head.color)((acc, sf) => acc.interpolate(sf.color, 0.5))
        c
      }
    }
    color
  }

}

/**
 * Pairs a searchterm to a color.
 *
 * The idea is to encode each search term with a color such that one can immediately spot an occurence in the views.
 *
 * @param pattern text to search for
 * @param color   associated color
 * @param active  is filter active
 */
class Filter(val pattern: String
             , override val color: Color
             , val active: Boolean) extends Fltr(color) {

  /** will be bound to the 'selected' state of the filter button */
  private val activeProperty: SimpleBooleanProperty = new SimpleBooleanProperty()

  private def isActive: Boolean = activeProperty.get()

  def bind(filterButton: FilterButton): Unit = activeProperty.bind(filterButton.selectedProperty())

  def unbind(): Unit = activeProperty.unbind()

  override def matches(searchTerm: String): Boolean = searchTerm.contains(pattern)

  def withActive(): Filter = new Filter(pattern, color, isActive)

}