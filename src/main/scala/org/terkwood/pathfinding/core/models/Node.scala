package org.terkwood.pathfinding.core.models

/** Represents a potentially walkable space
  * on a 2D grid.
  *
  * @param x        the x coordinate
  * @param y        the y coordinate
  * @param walkable Can this node be walked on?
  */
case class Node(x: Int,
                y: Int,
                walkable: Boolean = false)  {
  def equals(that: Node): Boolean = this.x == that.x && this.y == that.y
}



case class Point(x: Int, y: Int)
