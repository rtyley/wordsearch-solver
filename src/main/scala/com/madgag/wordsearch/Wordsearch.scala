package com.madgag.wordsearch

import scala.jdk.StreamConverters.*

case class Wordsearch(chars: Seq[Seq[Char]]) {
  val height: Int = chars.size
  val width: Int = chars.head.size

  val describe: String = s"$width x $height"

  private val characterCoordinates: Map[Char, Set[Coord]] = (for {
    y <- 0 until height
    x <- 0 until width
  } yield chars(y)(x) -> Coord(x, y)).toSet.groupMap(_._1)(_._2).withDefaultValue(Set.empty)

  private def isValid(coord: Coord): Boolean = coord.x >= 0 && coord.x < width && coord.y >= 0 && coord.y < height

  private def availableDirectionsAt(coord: Coord): Set[Direction] =
    Direction.All.filter(direction => isValid(coord.plus(direction))).toSet

  private def find(word: List[Char]): Map[Coord, Set[Direction]] = word.headOption.map { firstChar =>
    val possibleCoordsForThisChar = characterCoordinates(firstChar)
    word.tail match {
      case Nil => possibleCoordsForThisChar.map(coord => coord -> availableDirectionsAt(coord)).toMap
      case tailingChars =>
        (for {
          (endCoord, lineDirections) <- find(tailingChars)
        } yield endCoord -> lineDirections.filter { lineDirection =>
          possibleCoordsForThisChar.contains(endCoord.plus(tailingChars.size, lineDirection))
        }).filter(_._2.nonEmpty)
    }
  }.getOrElse(Map.empty)

  def find(word: String): Set[Line] = for {
    (coord, directions) <- find(word.reverse.toList).toSet
    direction <- directions
  } yield Line(coord, direction, word.length)

  def contains(word: String): Boolean = find(word).nonEmpty

  def highlight(lines: Set[Line]): Seq[String] = for (y <- 0 until height) yield (for x <- 0 until width yield {
    val coord = Coord(x, y)
    val highlight = lines.exists(_.coords.contains(coord))
    if (highlight) fansi.Color.LightGreen(chars(y)(x).toString) else fansi.Color.DarkGray(chars(y)(x).toString)
  }).reduce(_ ++ _).toString
}

object Wordsearch {

  def apply(gridText: String): Wordsearch =
    Wordsearch(gridText.split('\n').toSeq.filter(_.nonEmpty).map(_.toCharArray.toSeq))
}

