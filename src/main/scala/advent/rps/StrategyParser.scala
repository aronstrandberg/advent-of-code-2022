package com.aronstrandberg
package advent.rps

import advent.ProblemParser

import org.parboiled2.{Parser, ParserInput, Rule0, Rule1}

class StrategyParser(val input: ParserInput) extends Parser with ProblemParser {
  def parse(): Strategy = {
    unwrap[Strategy](this.Input.run())
  }

  def parseThrowingStrategy(): ThrowingStrategy = {
    unwrap[ThrowingStrategy](this.ThrowingInput.run())
  }

  private def Input: Rule1[Strategy] = rule {
    oneOrMore(Round).separatedBy(Newline) ~ EOI ~> (rounds => Strategy(rounds))
  }

  private def ThrowingInput: Rule1[ThrowingStrategy] = rule {
    oneOrMore(ThrowingRound).separatedBy(Newline) ~ EOI ~> (rounds => ThrowingStrategy(rounds))
  }

  private def Round: Rule1[RoundStrategy] = rule {
    OpponentWeaponRule ~ ' ' ~ WeaponRule ~> ((opponent, weapon) => RoundStrategy(opponent, weapon))
  }

  private def ThrowingRound: Rule1[ThrowingRoundStrategy] = rule {
    OpponentWeaponRule ~ ' ' ~ ThrowingStrategyRule ~> ((opponent, result) => ThrowingRoundStrategy(opponent, result))
  }

  private def OpponentWeaponRule: Rule1[OpponentWeapon] = rule(OpponentRock | OpponentPaper | OpponentScissor)

  private def OpponentRock: Rule1[OpponentWeapon] = rule('A' ~ push(OpponentWeapon.Rock))

  private def OpponentPaper: Rule1[OpponentWeapon] = rule('B' ~ push(OpponentWeapon.Paper))

  private def OpponentScissor: Rule1[OpponentWeapon] = rule('C' ~ push(OpponentWeapon.Scissors))

  private def WeaponRule: Rule1[Weapon] = rule(Rock | Paper | Scissor)

  private def Rock: Rule1[Weapon] = rule('X' ~ push(Weapon.Rock))

  private def Paper: Rule1[Weapon] = rule('Y' ~ push(Weapon.Paper))

  private def Scissor: Rule1[Weapon] = rule('Z' ~ push(Weapon.Scissors))

  private def ThrowingStrategyRule: Rule1[Result] = rule(Loss | Draw | Win)

  private def Loss: Rule1[Result] = rule('X' ~ push(Result.Loss))

  private def Draw: Rule1[Result] = rule('Y' ~ push(Result.Draw))

  private def Win: Rule1[Result] = rule('Z' ~ push(Result.Win))

  private def Newline: Rule0 = rule("\n")
}
