package com.aronstrandberg
package advent.cleanup

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CleanupParserSpec extends AnyFlatSpec with Matchers:
  "CleanupParser" should "parse input correctly" in {
    CleanupParser(CleanupExample.input).parse shouldBe CleanupExample.parsed
  }
