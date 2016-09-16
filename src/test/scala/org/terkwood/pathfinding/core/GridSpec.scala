package org.terkwood.pathfinding.core

import org.scalatest.{BeforeAndAfterEach, FlatSpec, MustMatchers}

class GridSpec extends FlatSpec with MustMatchers with BeforeAndAfterEach {
  override def beforeEach(): Unit = {
    beforeGridTest()
  }

  var beforeGridTest = () => ???

  "The grid function, called without a matrix" must "have correct size" in {
    ???
  }

  it must "update all nodes' walkable attributes" in {
    ???
  }

  beforeGridTest = () => ???

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

  beforeGridTest = () => ???

  "The grid function, called with a matrix, but without width or height" must "have correct size" in {
    ???
  }


}
