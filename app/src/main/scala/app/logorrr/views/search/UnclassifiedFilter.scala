package app.logorrr.views.search

class UnclassifiedFilter(filters: Set[Filter]) extends
  Filter("Unclassified", Filter.unClassifiedFilterColor) {

  override def applyMatch(searchTerm: String): Boolean = !filters.exists(_.applyMatch(searchTerm))

}
