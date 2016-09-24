package org.terkwood.pathfinding.core

import org.terkwood.pathfinding.core.models.{Node, Path, Point}

import scala.collection.mutable.ListBuffer

object backtrace {
  /**
    * Backtrace according to the parent records and return the path.
    * (including both start and end nodes)
    *
    * @param  node     End node
    * @param  parents  A map of node -> parentNode
    * @return the path
    */
  def apply(node: Node, parents: Map[Node, Node]): Path = {
    val path = ListBuffer(node.toPoint)
    var n = node
    while (parents.get(n).isDefined) {
      n = parents(n)
      path += Point(n.x, n.y)
    }
    path.reverse.toIndexedSeq
  }
}














