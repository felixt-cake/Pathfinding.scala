package org.terkwood.pathfinding.core

import org.scalatest.{FlatSpec, MustMatchers}

class GridSpec extends FlatSpec with MustMatchers {

  {
    val Width = 10
    val Height = 20
    val GridExample = Grid(Width, Height)

    "The grid function, called without a matrix" must "have correct size" in {
      ???
    }

    it must "update all nodes' walkable attributes" in {
      ???
    }
  }

  {
    ??? // Fixtures

    "The grid function, called with a matrix" must "have correct size" in {
      ???
    }

    it must "correctly default all nodes' walkable attributes" in {
      ???
    }

    it must "update all nodes' walkable attributes" in {
      ???
    }

    it must "return a correct answer for position validity query" in {
      ???
    }

    it must "return the correct neighbors" in {
      ???
    }
  }

  {
    ??? // Fixtures

    "The grid function, called with a matrix, but without width or height" must "have correct size" in {
      ???
    }
  }

}
