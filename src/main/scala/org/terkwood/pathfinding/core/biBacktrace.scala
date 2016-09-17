package org.terkwood.pathfinding.core

import org.terkwood.pathfinding.core.models.{Node, Path}

object biBacktrace {
  /**
    * Backtrace from start and end node, and return the path.
    * (including both start and end nodes)
    *
    * @param nodeA start or end node
    * @param nodeB end or start node
    */
  def apply(nodeA: Node, nodeB: Node): Path = {
    val pathA = backtrace(nodeA)
    val pathB = backtrace(nodeB)
    pathA ++ pathB.reverse
  }
}
