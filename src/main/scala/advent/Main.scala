package com.aronstrandberg
package advent

import scala.io.Source.stdin

object Runner {
  def run[Input, Solution](problem: Problem[Input, Solution]): Solution = {
    val solution = problem.run(slurp)
    println(solution)
    solution
  }

  private def slurp: String = {
    stdin.getLines.toList.mkString("\n")
  }
}

object Main {
  def main(args: Array[String]): Unit = {
  }
}
