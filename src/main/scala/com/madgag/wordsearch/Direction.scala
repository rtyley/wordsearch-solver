package com.madgag.wordsearch

import com.madgag.wordsearch.Direction.magnitudeFor

object Direction {
  def magnitudeFor(heading: Int): Int = if (heading % 4 == 0) 0 else if (heading > 4) 1 else -1

  val All: Seq[Direction] = for {
    (arrow, heading) <- "→↑↗↖←↙↓↘".zipWithIndex
  } yield Direction(heading, arrow)

  assert(All.map(_.xDiff).sum == 0)
  assert(All.map(_.yDiff).sum == 0)
}

case class Direction(heading: Int, arrow: Char) {
  val xDiff: Int = magnitudeFor((heading + 2) % 8)
  val yDiff: Int = magnitudeFor(heading)

  override val toString: String = arrow.toString
}