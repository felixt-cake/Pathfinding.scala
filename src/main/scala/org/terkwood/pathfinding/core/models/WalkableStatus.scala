package org.terkwood.pathfinding.core.models

/** A convenience trait to help with the formation
  * of matrices which indicate whether or not
  * various tiles on a grid are walkable.
  *
  * We found naming this explicitly to make the
  * code a bit more readable.
  */
sealed trait WalkableStatus
case object Walk extends WalkableStatus
case object NoWalk extends WalkableStatus

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
