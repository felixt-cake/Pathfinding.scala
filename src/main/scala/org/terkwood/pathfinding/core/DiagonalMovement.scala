package org.terkwood.pathfinding.core

object DiagonalMovement {

  sealed trait DiagonalMovementOption {
    val value: Int
  }

  case object Always extends DiagonalMovementOption {
    override val value: Int = 1
  }

  case object Never extends DiagonalMovementOption {
    override val value: Int = 2
  }

  case object IfAtMostOneObstacle extends DiagonalMovementOption {
    override val value: Int = 3
  }

  case object OnlyWhenNoObstacles extends DiagonalMovementOption {
    override val value: Int = 4
  }

}
