package org.terkwood.pathfinding.core

object WalkableStatus {
  def from(a: Int) = a match {
    case 0 => Walk
    case _ => NoWalk
  }
  def from(a: Boolean) = a match {
    case false => Walk
    case _ => NoWalk
  }
}

sealed trait WalkableStatus
case object Walk extends WalkableStatus
case object NoWalk extends WalkableStatus
