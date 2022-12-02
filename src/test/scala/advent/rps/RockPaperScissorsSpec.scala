package com.aronstrandberg
package advent.rps

import advent.ProblemParser

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success, Try}

val example =
  """
    |A Y
    |B X
    |C Z
    |""".stripMargin.strip

val minimal = "A Y"

val minimalParsed = Strategy(Seq(RoundStrategy(OpponentWeapon.Rock, Weapon.Paper)))

val parsed = Strategy(Seq(
  RoundStrategy(OpponentWeapon.Rock, Weapon.Paper),
  RoundStrategy(OpponentWeapon.Paper, Weapon.Rock),
  RoundStrategy(OpponentWeapon.Scissors, Weapon.Scissors),
))

class StrategyParserSpec extends AnyFlatSpec {
  "StrategyParser" should "parse a minimal example" in {
    assert(new StrategyParser(minimal).parse() == minimalParsed)
  }
  "StrategyParser" should "parse correctly" in {
    assert(new StrategyParser(example).parse() == parsed)
  }
}

class RockPaperScissorsSpec extends AnyFlatSpec {
  "RockPaperScissors" should "return the right result" in {
    assert(new RockPaperScissors().run(example) == 15)
  }

  "score" should "calculate score correctly" in {
    assert(score(RoundStrategy(OpponentWeapon.Rock, Weapon.Paper)) == 8)
    assert(score(RoundStrategy(OpponentWeapon.Paper, Weapon.Rock)) == 1)
    assert(score(RoundStrategy(OpponentWeapon.Scissors, Weapon.Scissors)) == 6)
  }
}

class ThrowingRockPaperScissorsSpec extends AnyFlatSpec {
  "RockPaperScissors" should "return the right result" in {
    assert(new ThrowingRockPaperScissors().run(example) == 12)
  }

  "score" should "calculate score correctly" in {
    assert(score(RoundStrategy(OpponentWeapon.Rock, calculate(ThrowingRoundStrategy(OpponentWeapon.Rock, Result.Draw)))) == 4)
    assert(score(RoundStrategy(OpponentWeapon.Paper, calculate(ThrowingRoundStrategy(OpponentWeapon.Paper, Result.Loss)))) == 1)
    assert(score(RoundStrategy(OpponentWeapon.Scissors, calculate(ThrowingRoundStrategy(OpponentWeapon.Scissors, Result.Win)))) == 7)
  }
}
