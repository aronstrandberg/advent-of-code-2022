package com.aronstrandberg
package advent

import org.parboiled2.{CharPredicate, Parser, ParserInput, Rule0, Rule1}

import scala.util.{Failure, Success}

case class CalorieCountInput(counts: Seq[CalorieCountValue])

case class CalorieCountValue(items: Seq[Int])

class CalorieListParser(val input: ParserInput) extends Parser {
  def Input: Rule1[CalorieCountInput] = rule {
    oneOrMore(CalorieCount).separatedBy(EmptyLine) ~ EOI ~> (counts => CalorieCountInput(counts))
  }

  private def CalorieCount: Rule1[CalorieCountValue] = rule {
    oneOrMore(Number).separatedBy(Newline) ~> (numbers => CalorieCountValue(numbers))
  }

  private def Number: Rule1[Int] = rule {
    capture(Digits) ~> (_.toInt)
  }

  private def Digits: Rule0 = rule {
    oneOrMore(CharPredicate.Digit)
  }

  private def EmptyLine: Rule0 = rule {
    2.times(Newline)
  }

  private def Newline: Rule0 = rule {
    "\n"
  }
}

trait CalorieCountingProblem extends Problem[CalorieCountInput, Int] {
  def parse(input: String): CalorieCountInput = {
    new CalorieListParser(input).Input.run() match {
      case Failure(exception) => {
        println("Failed to parse input")
        throw exception
      }
      case Success(value) => value
    }
  }
}

/**
 * Day 1 — Calorie counting
 * Part 1
 */
class CalorieCounting extends CalorieCountingProblem {
  override def solve(input: CalorieCountInput): Int = {
    input.counts.map(_.items.sum).max
  }
}

/**
 * Part 2
 */
class TopCalorieCounting extends CalorieCountingProblem {
  override def solve(input: CalorieCountInput): Int = {
    input.counts.map(_.items.sum).sorted.reverse.take(3).sum
  }
}
