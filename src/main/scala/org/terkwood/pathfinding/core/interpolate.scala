package org.terkwood.pathfinding.core

import org.terkwood.pathfinding.core.models.{Point, Path}

import scala.collection.mutable.ListBuffer

object interpolate {
  /**
    * Given the start and end coordinates, return all the coordinates lying
    * on the line formed by these coordinates, based on Bresenham's algorithm.
    * @see http://en.wikipedia.org/wiki/Bresenham's_line_algorithm#Simplification
    *
    * @param  x0  Start x coordinate
    * @param  y0 Start y coordinate
    * @param  x1 End x coordinate
    * @param  y1 End y coordinate
    * @return The coordinates on the line
    */
  def apply(x0: Int, y0: Int, x1: Int, y1: Int): Path = {
    import math.abs
    val dx = abs(x1 - x0)
    val dy = abs(y1 - y0)

    val sx = if (x0 < x1)  1 else -1
    val sy = if (y0 < y1)  1 else -1

    var err = dx - dy

    val line = ListBuffer.empty[Point]

    var x = x0
    var y = y0

    while (true) {
      line += Point(x, y)

      if (x == x1 && y == y1)
        return line.toIndexedSeq

      val e2 = 2 * err

      if (e2 > -dy) {
        err = err - dy
        x = x + sx
      }

      if (e2 < dx) {
        err = err + dx
        y = y + sy
      }
    }

    line.toIndexedSeq
  }
}
