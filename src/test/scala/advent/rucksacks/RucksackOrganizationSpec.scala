package com.aronstrandberg
package advent.rucksacks

import org.scalatest.flatspec.AnyFlatSpec

class RucksackOrganizationSpec extends AnyFlatSpec:
  "RockPaperScissors" should "return the right result" in {
    assert(new RucksackOrganization().solve(RucksackOrganizationExample.parsed) == 157)
    assert(new RucksackOrganization().run(RucksackOrganizationExample.input) == 157)
  }

  "totalCommonPriority" should "return the total common priority" in {
    assert(RucksackOrganizationExample.parsed.totalCommonPriority == 157)
  }

  "common" should "return the common elements" in {
    assert(RucksackOrganizationExample.a.common == Set(RucksackItem('p')))
    assert(RucksackOrganizationExample.b.common == Set(RucksackItem('L')))
    assert(RucksackOrganizationExample.c.common == Set(RucksackItem('P')))
    assert(RucksackOrganizationExample.d.common == Set(RucksackItem('v')))
    assert(RucksackOrganizationExample.e.common == Set(RucksackItem('t')))
    assert(RucksackOrganizationExample.f.common == Set(RucksackItem('s')))
  }

  "priority" should "return the priority of the common elements" in {
    assert(RucksackOrganizationExample.a.commonPriority == 16)
    assert(RucksackOrganizationExample.b.commonPriority == 38)
    assert(RucksackOrganizationExample.c.commonPriority == 42)
    assert(RucksackOrganizationExample.d.commonPriority == 22)
    assert(RucksackOrganizationExample.e.commonPriority == 20)
    assert(RucksackOrganizationExample.f.commonPriority == 19)
  }

class GroupedRucksackOrganizationSpec extends AnyFlatSpec:
  "badge" should "return the correct badge of a group" in {
    assert(RucksackOrganizationExample.groupA.badge == RucksackItem('r'))
    assert(RucksackOrganizationExample.groupB.badge == RucksackItem('Z'))
  }

  "priority" should "return the correct priority of a group" in {
    assert(RucksackOrganizationExample.groupA.badge.priority == 18)
    assert(RucksackOrganizationExample.groupB.badge.priority == 52)
  }

  "priority" should "return the correct priority of a group input" in {
    val input = GroupedRucksackInput(Seq(
      RucksackOrganizationExample.groupA,
      RucksackOrganizationExample.groupB
    ))
    assert(input.prioritySum == 70)
  }
