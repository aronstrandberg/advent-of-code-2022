package com.aronstrandberg
package advent

trait Problem[Input, Solution] {
  def parse(input: String): Input
  def solve(input: Input): Solution
  def run(input: String): Solution = solve(parse(input))
}
