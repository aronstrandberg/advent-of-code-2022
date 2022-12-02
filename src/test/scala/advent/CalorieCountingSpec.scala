package com.aronstrandberg
package advent

import org.scalatest.flatspec.AnyFlatSpec

class CalorieCountingSpec extends AnyFlatSpec {
  "CalorieCounting" should "parse a minimal example" in {
    assert(CalorieCounting().parse(minimal) == minimalParsed)
  }
  "CalorieCounting" should "parse correctly" in {
    assert(CalorieCounting().parse(example) == parsed)
  }
  "CalorieCounting" should "return 24000" in {
    assert(CalorieCounting().run(example) == 24000)
  }
}

val example = """
|1000
|2000
|3000
|
|4000
|
|5000
|6000
|
|7000
|8000
|9000
|
|10000
|""".stripMargin.strip

val parsed = CalorieCountInput(
  Seq(
    CalorieCountValue(Seq(1000, 2000, 3000)),
    CalorieCountValue(Seq(4000)),
    CalorieCountValue(Seq(5000, 6000)),
    CalorieCountValue(Seq(7000, 8000, 9000)),
    CalorieCountValue(Seq(10000)),
  )
)

val minimal = "1000"

val minimalParsed = CalorieCountInput(Seq(CalorieCountValue(Seq(1000))))
