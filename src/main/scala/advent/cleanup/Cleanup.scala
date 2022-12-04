package com.aronstrandberg
package advent.cleanup

import advent.Problem

case class CleanupInput(pairs: Seq[Pairing]):
  def overlapping: Int = {
    pairs.count(_.overlaps)
  }

  def containing: Int = {
    pairs.count(_.contains)
  }

type Pair = (Assignment, Assignment)

case class Pairing(pair: Pair):
  def overlaps: Boolean = pair match
    case (one, two) => one.toSet.intersect(two.toSet).nonEmpty

  def contains: Boolean = pair match
    case (one, two) => one.toSet.subsetOf(two.toSet) || two.toSet.subsetOf(one.toSet)

type Assignment = Range

class Cleanup extends Problem[CleanupInput, Int]:
  override def parse(input: String): CleanupInput = {
    CleanupParser(input).parse
  }

  override def solve(input: CleanupInput): Int = {
    input.overlapping
  }
