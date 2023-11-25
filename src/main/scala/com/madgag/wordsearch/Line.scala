package com.madgag.wordsearch

case class Line(startCoord: Coord, direction: Direction, length: Int) {
  val coords: Seq[Coord] = (0 until length).map(startCoord.plus(_, direction))
}
