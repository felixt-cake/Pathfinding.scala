package org.terkwood.pathfinding.core.models

/** Represents a potentially walkable space
  * on a 2D grid.
  * @param x the x coordinate
  * @param y the y coordinate
  * @param walkable Can this node be walked on?
  * @param parent Needs to mimic javascript
  *               parent functionality.  Good times.
  */
case class Node(x: Int,
                y: Int,
                walkable: Boolean = false,
                parent: Option[Node] = None)


case class Point(x: Int, y: Int)
