package com.aronstrandberg
package advent.calories

import advent.Problem

import org.parboiled2.{CharPredicate, Parser, ParserInput, Rule0, Rule1}

import scala.util.{Failure, Success}

case class CalorieCountInput(counts: Seq[CalorieCountValue])

case class CalorieCountValue(items: Seq[Int])

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
    input.counts.map(_.items.sum).sorted(Ordering.Int.reverse).take(3).sum
  }
}
