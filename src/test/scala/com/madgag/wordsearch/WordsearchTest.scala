package com.madgag.wordsearch

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class WordsearchTest extends AnyFlatSpec with Matchers {
  it should "solve a tiny wordsearch" in {
    val wordsearch = Wordsearch(
      """FFBF
        |FIOO
        |FROG
        |""".stripMargin)

    wordsearch.describe shouldBe "4 x 3"
    wordsearch.contains("FOG") shouldBe true
    wordsearch.find("FOG") should have size 2
  }

  it should "solve the big music crossword" in {
    val wordsearch = Wordsearch(
      """RSTDECRESCENDOO
        |EOHSHORTENBECSM
        |SNEAHELEEPLETIS
        |TOQERTAZSXEDILT
        |SRUVRTPYVCTWIEA
        |IILODTIMBREUNNC
        |CTOPEWTCANENRCC
        |DYNAMICSUCTIDEA
        |ULGMHTHINLHEROT
        |RKEUOSRHETAHCAO
        |ABJGKWWEWROTRED
        |TRNXAOHLBASSISR
        |IOWZNTOILLNISOO
        |OFTEMPOWFIEHTHN
        |NODIMINUENDOISW""".stripMargin)

    wordsearch.describe shouldBe "15 x 15"
    wordsearch.contains("DIMINUENDO") shouldBe true
  }
}
