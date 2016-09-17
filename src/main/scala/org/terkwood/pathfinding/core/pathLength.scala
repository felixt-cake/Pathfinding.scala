package org.terkwood.pathfinding.core

import org.terkwood.pathfinding.core.models.Path

object pathLength {
  /**
    * Compute the length of the path.
    *
    * @param path The path
    * @return The length of the path
    */
  def apply(path: Path): Double = {
    def helper(i: Int = 1, sum: Double = 0): Double =
      if (i == path.length)
        sum
      else {
        val a = path(i - 1)
        val b = path(i)
        val dx = a.x - b.x
        val dy = a.y - b.y
        helper(i + 1, sum + math.sqrt(dx * dx + dy * dy).toFloat)
      }

    helper()
  }
}
