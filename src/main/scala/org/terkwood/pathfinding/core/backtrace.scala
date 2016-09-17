package org.terkwood.pathfinding.core

import org.terkwood.pathfinding.core.models.{Point, Path, Node}

import scala.collection.mutable.ListBuffer

object backtrace {
  /**
    * Backtrace according to the parent records and return the path.
    * (including both start and end nodes)
    *
    * @param  node End node
    * @return the path
    */
  def apply(node: Node): Path = {
    val path = ListBuffer(node.toPoint)
    var n = node
    while (n.parent.isDefined) {
      n = n.parent.get
      path += Point(n.x, n.y)
    }
    path.reverse.toIndexedSeq
  }
}














