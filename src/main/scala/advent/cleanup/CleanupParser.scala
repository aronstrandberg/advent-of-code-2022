package com.aronstrandberg
package advent.cleanup

import advent.ProblemParser

import org.parboiled2.{CharPredicate, Parser, ParserInput, Rule0, Rule1}

class CleanupParser(val input: ParserInput) extends Parser with ProblemParser:
  def parse: CleanupInput = {
    val result = this.Input.run()
    unwrap(result)
  }
  def Input: Rule1[CleanupInput] = rule {
    oneOrMore(PairingRule).separatedBy(Newline) ~ EOI ~> (pairings => CleanupInput(pairings))
  }
  private def PairingRule: Rule1[Pairing] = rule {
    Assignment ~ ',' ~ Assignment ~> ((one: Assignment, two: Assignment) => Pairing((one, two)))
  }
  private def Assignment: Rule1[Assignment] = rule {
    Number ~ '-' ~ Number ~> ((start, end) => start to end)
  }
  private def Number: Rule1[Int] = rule(capture(Digits) ~> (_.toInt))
  private def Digits: Rule0 = rule(oneOrMore(CharPredicate.Digit))
  private def Newline: Rule0 = rule("\n")
