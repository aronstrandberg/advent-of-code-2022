package com.aronstrandberg
package advent.rucksacks

object RucksackOrganizationExample:
  def input: String =
    """
      |vJrwpWtwJgWrhcsFMMfFFhFp
      |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
      |PmmdzqPrVvPwwTWBwg
      |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
      |ttgJtRGJQctTZtZT
      |CrZsJsPPZsGzwwsLwLmpwMDw
      |""".stripMargin.strip

  def a: RucksackContents = new RucksackContents("vJrwpWtwJgWrhcsFMMfFFhFp")

  def b: RucksackContents = new RucksackContents("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")

  def c: RucksackContents = new RucksackContents("PmmdzqPrVvPwwTWBwg")

  def d: RucksackContents = new RucksackContents("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")

  def e: RucksackContents = new RucksackContents("ttgJtRGJQctTZtZT")

  def f: RucksackContents = new RucksackContents("CrZsJsPPZsGzwwsLwLmpwMDw")

  def parsed: RucksackInput = RucksackInput(Seq(
    a, b, c, d, e, f
  ))

  def groupA: RucksackGroup = RucksackGroup(a, b, c)

  def groupB: RucksackGroup = RucksackGroup(d, e, f)
