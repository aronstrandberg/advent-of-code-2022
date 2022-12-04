package com.aronstrandberg
package advent.cleanup

object CleanupExample:
  def input: String = {
    """
      |2-4,6-8
      |2-3,4-5
      |5-7,7-9
      |2-8,3-7
      |6-6,4-6
      |2-6,4-8
      |""".stripMargin.strip
  }

  def a: Pairing = Pairing(2 to 4, 6 to 8)

  def b: Pairing = Pairing(2 to 3, 4 to 5)

  def c: Pairing = Pairing(5 to 7, 7 to 9)

  def d: Pairing = Pairing(2 to 8, 3 to 7)

  def e: Pairing = Pairing(6 to 6, 4 to 6)

  def f: Pairing = Pairing(2 to 6, 4 to 8)

  def parsed: CleanupInput = CleanupInput(Seq(
    a, b, c, d, e, f
  ))
