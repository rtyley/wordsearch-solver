package com.madgag.wordsearch

case class Coord(x: Int, y: Int) {
  def plus(direction: Direction): Coord = plus(1, direction)

  def plus(length: Int, direction: Direction): Coord = Coord(x + (direction.xDiff * length), y + (direction.yDiff * length))
}
