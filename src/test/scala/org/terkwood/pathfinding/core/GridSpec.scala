package org.terkwood.pathfinding.core

import org.scalatest.{FlatSpec, MustMatchers}

class GridSpec extends FlatSpec with MustMatchers {

  {
    val width = 10
    val height = 20
    val grid = Grid(width, height)

    "A grid, created without a matrix" must "have correct size" in {
      grid.width mustEqual width
      grid.height mustEqual height

      grid.nodes.length mustEqual height

      Range(0, height) foreach {
        grid.nodes(_).length mustEqual width
      }
    }

    it must "set all nodes' walkable attributes" in {
      for {
        i <- 0 until height
        j <- 0 until width
      } grid isWalkableAt(j, i) mustBe true
    }
  }

  {
    val matrix: IndexedSeq[IndexedSeq[Boolean]] = IndexedSeq(
      IndexedSeq(true, false, false, true),
      IndexedSeq(false, true, false, false),
      IndexedSeq(false, true, false, false),
      IndexedSeq(false, false, false, false),
      IndexedSeq(true, false, false, true)
    )

    val width: Int = matrix.head.length
    val height: Int = matrix.length

    val grid = Grid(width, height, matrix)


    def enumPos2[A](f: (Int, Int) => A) =
      for {y <- 0 until height
           x <- 0 until width}
        yield
          f(x, y)

    def enumPos3[A](f: (Int, Int, Grid) => A) =
      for {y <- 0 until height
           x <- 0 until width}
        yield
          f(x, y, grid)

    def enumPos4[A, B](f: (B, Int, Int, Grid) => A, b: B) =
      for {y <- 0 until height
           x <- 0 until width}
        yield
          f(b, x, y, grid)

    "A grid, created with a matrix" must "have correct size" in {
      grid.width mustEqual width
      grid.height mustEqual height

      grid.nodes.length mustEqual height

      for {i <- 0 until height}
        grid.nodes(i).length mustEqual width
    }


    it must "correctly default all nodes' walkable attributes" in {
      enumPos3((x, y, g) => {
        if (matrix(y)(x)) {
          g isWalkableAt(x, y) mustBe false
        } else {
          g isWalkableAt(x, y) mustBe true
        }
      })
    }

    it must "set all nodes' walkable attributes" in {
      var result = grid

      enumPos2 { (x, y) =>
        result = grid withWalkableAt(x, y, walkable = false)
      }

      enumPos2 { (x, y) =>
        result isWalkableAt(x, y) mustBe false
      }

      enumPos2 { (x, y) =>
        result = result withWalkableAt(x, y, walkable = true)
      }

      enumPos2 { (x, y) =>
        result isWalkableAt(x, y) mustBe true
      }
    }


    it must "return a correct answer for position validity query" in {
      val asserts = Seq(
        (0, 0, true),
        (0, height - 1, true),
        (width - 1, 0, true),
        (width - 1, height - 1, true),
        (-1, -1, false),
        (0, -1, false),
        (-1, 0, false),
        (0, height, false),
        (width, 0, false),
        (width, height, false)
      )

      asserts foreach { case (p, q, z) =>
        grid.isInside(p, q) mustEqual z
      }
    }

    it must "return the correct neighbors" in {
      grid getNeighbors(grid.nodes(1)(0), DiagonalMovement.Never) mustEqual grid.nodes(2)(0)

      def compare(a: Node, b: Node) =
        a.x * 100 + a.y > b.x * 100 - b.y

      grid
        .getNeighbors(grid.nodes(0)(2), DiagonalMovement.IfAtMostOneObstacle)
        .sortWith(compare) mustEqual
        Seq(grid.nodes(0)(1), grid.nodes(1)(2), grid.nodes(1)(3))
          .sortWith(compare)
    }
  }

  {
    val matrix = IndexedSeq(
      IndexedSeq(true, false, false, true),
      IndexedSeq(false, true, false, false),
      IndexedSeq(false, true, false, false),
      IndexedSeq(false, false, false, false),
      IndexedSeq(true, false, false, true))

    val grid = Grid(matrix)

    "A grid, created with a matrix, and without width or height" must "have correct size" in {
      val height = matrix.length
      val width = matrix.head.length

      grid.width mustEqual width
      grid.height mustEqual height

      grid.nodes.length mustEqual height
      for {i <- 0 until height}
        grid.nodes(i).length mustEqual width
    }
  }
}
