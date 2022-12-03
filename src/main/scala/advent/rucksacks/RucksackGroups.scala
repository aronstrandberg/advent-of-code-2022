package com.aronstrandberg
package advent.rucksacks

import advent.Problem

case class GroupedRucksackInput(groups: Seq[RucksackGroup]):
  def prioritySum: Int = {
    groups.map(_.badge).map(_.priority).sum
  }

case class RucksackGroup(rucksacks: (RucksackContents, RucksackContents, RucksackContents)):
  def badge: RucksackItem = {
    val common = rucksacks match
      case (one, two, three) => one.items.toSet.intersect(two.items.toSet).intersect(three.items.toSet)
    assume(common.size == 1)
    common.head
  }

class GroupedRucksackOrganization extends Problem[GroupedRucksackInput, Int]:
  override def parse(input: String): GroupedRucksackInput = {
    val groups = input.split("\n").toSeq
      .map(new RucksackContents(_))
      .grouped(3)
      .map(_ match
        case Seq(a: RucksackContents, b: RucksackContents, c: RucksackContents) => (a, b, c)
      )
      .map(RucksackGroup.apply)
      .toSeq
    GroupedRucksackInput(groups)
  }

  override def solve(input: GroupedRucksackInput): Int = input.prioritySum
