package com.madgag.wordsearch

import java.nio.file.{Files, Path, Paths}
import scala.jdk.StreamConverters.*

case class Dictionary(words: Seq[String]) {
  def withWordsLongerThan(length: Int): Dictionary = copy(words.filter(_.length > length))
}

object Dictionary {
  lazy val systemDict: Dictionary = Dictionary(
    Files.lines(Paths.get("/usr/share/dict/words")).toScala(Seq).map(_.toUpperCase.filter(_.isLetter)).filter(_.nonEmpty)
  )
}
