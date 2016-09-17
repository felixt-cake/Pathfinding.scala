package org.terkwood.pathfinding.core.finders

import org.terkwood.pathfinding.core.models.{Grid, Path}

trait Finder {
  /**
    * Find and return the the path.
    *
    * @return Path The path, including both start and
    *         end positions.
    */
  def findPath(startX: Int, startY: Int,
               endX: Int, endY: Int,
               grid: Grid): Path
}
