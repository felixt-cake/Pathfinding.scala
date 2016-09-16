package org.terkwood.pathfinding.core

sealed trait DiagonalMovement { val value: Int }
case object Always extends DiagonalMovement {
  override val value: Int = 1
}
case object Never extends  DiagonalMovement {
  override val value: Int = 2
}
case object IfAtMostOneObstacle extends DiagonalMovement {
  override val value: Int = 3
}
case object OnlyWhenNoObstacles extends DiagonalMovement {
  override val value: Int = 4
}
