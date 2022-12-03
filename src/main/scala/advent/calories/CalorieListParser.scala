package com.aronstrandberg
package advent.calories

import org.parboiled2.{CharPredicate, Parser, ParserInput, Rule0, Rule1}

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
