package com.aronstrandberg
package advent.rucksacks

import advent.Problem

val chars = ('a' to 'z') ++ ('A' to 'Z')

case class RucksackInput(rucksacks: Seq[RucksackContents]):
  def totalCommonPriority: Int = {
    rucksacks.map(_.commonPriority).sum
  }

case class RucksackContents(compartments: (Seq[RucksackItem], Seq[RucksackItem])):
  def items: Seq[RucksackItem] = compartments match
    case (one, two) => one ++ two
  def this(items: Seq[RucksackItem]) = this(items.splitAt(items.length/2))
  def this(input: String) = this(input.toSeq.map(RucksackItem.apply))

  def common: Set[RucksackItem] = {
    compartments match
      case (one, two) => one.toSet.intersect(two.toSet)
  }

  def commonPriority: Int = {
    common.map(_.priority).sum
  }

case class RucksackItem(item: Char):
  require(chars.contains(item))

  def priority: Int = {
    chars.indexOf(item) + 1
  }

class RucksackOrganization extends Problem[RucksackInput, Int]:
  override def parse(input: String): RucksackInput = {
    RucksackInput(input.split("\n").toSeq.map(new RucksackContents(_)))
  }

  override def solve(input: RucksackInput): Int = input.totalCommonPriority
