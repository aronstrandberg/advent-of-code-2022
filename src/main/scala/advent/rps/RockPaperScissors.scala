package com.aronstrandberg
package advent.rps

import advent.{Problem, ProblemParser}

import org.parboiled2.{Parser, ParserInput, Rule0, Rule1}

case class Strategy(rounds: Seq[RoundStrategy])
case class ThrowingStrategy(rounds: Seq[ThrowingRoundStrategy])

case class RoundStrategy(opponent: OpponentWeapon, weapon: Weapon)
case class ThrowingRoundStrategy(opponent: OpponentWeapon, result: Result)

enum Result(val score: Int) {
  case Win extends Result(6)
  case Draw extends Result(3)
  case Loss extends Result(0)
}

def play(round: RoundStrategy): Result = {
  round match {
    case RoundStrategy(OpponentWeapon.Rock, Weapon.Rock) => Result.Draw
    case RoundStrategy(OpponentWeapon.Rock, Weapon.Paper) => Result.Win
    case RoundStrategy(OpponentWeapon.Rock, Weapon.Scissors) => Result.Loss

    case RoundStrategy(OpponentWeapon.Paper, Weapon.Rock) => Result.Loss
    case RoundStrategy(OpponentWeapon.Paper, Weapon.Paper) => Result.Draw
    case RoundStrategy(OpponentWeapon.Paper, Weapon.Scissors) => Result.Win

    case RoundStrategy(OpponentWeapon.Scissors, Weapon.Rock) => Result.Win
    case RoundStrategy(OpponentWeapon.Scissors, Weapon.Paper) => Result.Loss
    case RoundStrategy(OpponentWeapon.Scissors, Weapon.Scissors) => Result.Draw
  }
}

def calculate(round: ThrowingRoundStrategy): Weapon = {
  round match {
    case ThrowingRoundStrategy(OpponentWeapon.Rock, Result.Loss) => Weapon.Scissors
    case ThrowingRoundStrategy(OpponentWeapon.Rock, Result.Draw) => Weapon.Rock
    case ThrowingRoundStrategy(OpponentWeapon.Rock, Result.Win) => Weapon.Paper

    case ThrowingRoundStrategy(OpponentWeapon.Paper, Result.Loss) => Weapon.Rock
    case ThrowingRoundStrategy(OpponentWeapon.Paper, Result.Draw) => Weapon.Paper
    case ThrowingRoundStrategy(OpponentWeapon.Paper, Result.Win) => Weapon.Scissors

    case ThrowingRoundStrategy(OpponentWeapon.Scissors, Result.Loss) => Weapon.Paper
    case ThrowingRoundStrategy(OpponentWeapon.Scissors, Result.Draw) => Weapon.Scissors
    case ThrowingRoundStrategy(OpponentWeapon.Scissors, Result.Win) => Weapon.Rock
  }
}

def score(round: RoundStrategy): Int = {
  round.weapon.score + play(round).score
}

enum OpponentWeapon {
  case Rock extends OpponentWeapon
  case Paper extends OpponentWeapon
  case Scissors extends OpponentWeapon
}

enum Weapon(val score: Int) {
  case Rock extends Weapon(1)
  case Paper extends Weapon( 2)
  case Scissors extends Weapon( 3)
}

class RockPaperScissors extends Problem[Strategy, Int] {
  override def parse(input: String): Strategy = {
    new StrategyParser(input).parse()
  }
  override def solve(input: Strategy): Int = {
    input.rounds.map(round => score(round)).sum
  }
}

class ThrowingRockPaperScissors extends Problem[ThrowingStrategy, Int] {
  override def parse(input: String): ThrowingStrategy = {
    new StrategyParser(input).parseThrowingStrategy()
  }
  override def solve(input: ThrowingStrategy): Int = {
    input.rounds
      .map(round => RoundStrategy(round.opponent, calculate(round)))
      .map(round => score(round))
      .sum
  }
}

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
