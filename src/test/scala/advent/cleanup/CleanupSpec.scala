package com.aronstrandberg
package advent.cleanup

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.should

class CleanupSpec extends AnyFlatSpec with Matchers:
  "overlaps" should "verify whether a pairing is overlapping" in {
    CleanupExample.a.overlaps shouldBe false
    CleanupExample.b.overlaps shouldBe false
    CleanupExample.c.overlaps shouldBe false
    CleanupExample.d.overlaps shouldBe true
    CleanupExample.e.overlaps shouldBe true
    CleanupExample.f.overlaps shouldBe false
  }

  "overlapping" should "count the number of overlapping pairs" in {
    CleanupExample.parsed.overlapping shouldBe 2
  }
