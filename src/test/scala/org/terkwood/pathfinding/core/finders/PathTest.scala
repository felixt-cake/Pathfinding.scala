package org.terkwood.pathfinding.core.finders

import org.scalatest.{FlatSpec, MustMatchers}
import org.terkwood.pathfinding.core.models.{Grid, Point}

case class PathTestOptions(name: String,
                           finder: Finder,
                           optimal: Boolean)

class PathTest extends FlatSpec with MustMatchers with PathTestScenarios {
  val Finders: Seq[PathTestOptions] = Seq(
    PathTestOptions("AStar", new AStarFinder, optimal = true)
  )

  def finderTest(options: PathTestOptions) = {
    val name = options.name
    val finder = options.finder
    val optimal = options.optimal


    def scenarioTest(id: Int, startX: Int, startY: Int,
                     endX: Int, endY: Int,
                     grid: Grid, expectedLength: Int): Unit = {

      s"The $name pathfinder" must s"solve maze $id" in {
        val path = finder.findPath(startX, startY, endX, endY, grid);
        if (optimal) {
          path.length mustEqual expectedLength
        } else {
          path.head mustEqual Point(startX, startY)
          path.last mustEqual Point(endX, endY)
        }
      }
    }


    PathTestScenarios foreach { case Scenario(id, startX, startY, endX, endY, matrix, expectedLength) =>
      val width = matrix.head.length
      val height = matrix.length
      val grid = Grid(width, height, matrix)
      scenarioTest(id,
        startX, startY,
        endX, endY,
        grid,
        expectedLength
      )
    }

  }


  Finders foreach {
    finderTest
  }
}
