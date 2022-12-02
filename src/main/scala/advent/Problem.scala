package com.aronstrandberg
package advent

import scala.util.{Failure, Success, Try}

trait Problem[Input, Solution] {
  def parse(input: String): Input
  def solve(input: Input): Solution
  def run(input: String): Solution = solve(parse(input))
}

trait ProblemParser {
  def unwrap[Input](result: Try[Input]): Input = {
    result match {
      case Failure(exception) => {
        println("Failed to parse input")
        throw exception
      }
      case Success(value) => value
    }
  }
}
