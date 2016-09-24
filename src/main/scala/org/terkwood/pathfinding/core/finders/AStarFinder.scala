package org.terkwood.pathfinding.core.finders

import org.terkwood.pathfinding.core.backtrace
import org.terkwood.pathfinding.core.heuristics.{Heuristic, manhattan, octile}
import org.terkwood.pathfinding.core.models.DiagonalMovement.DiagonalMovementOption
import org.terkwood.pathfinding.core.models._

import scala.collection.immutable.IndexedSeq
import scala.collection.mutable


/** Represents a potentially walkable space
  * on a 2D grid.
  *
  * @param node The node
  * @param f    Used by AStar finder
  * @param g    Used by AStar finder
  */
case class AStarInfo(node: Node,
                     f: Option[Double] = None,
                     g: Option[Double] = None,
                     h: Option[Double] = None) extends Ordered[AStarInfo] {

  override def compare(that: AStarInfo): Int = (that.f.getOrElse(0d) - this.f.getOrElse(0d)).toInt
}


/**
  * A* path-finder. Based upon https://github.com/bgrins/javascript-astar
  *
  * @param diagonalMovement Allowed diagonal movement.
  * @param desiredHeuristic function to estimate the distance
  *                         (defaults to manhattan).
  * @param weight           Weight to apply to the heuristic to allow for
  *                         suboptimal paths, in order to speed up the search.
  */
class AStarFinder(diagonalMovement: DiagonalMovementOption = DiagonalMovement.Never,
                  desiredHeuristic: Option[Heuristic] = None,
                  weight: Double = 1) extends Finder {

  // When diagonal movement is allowed the manhattan heuristic is not
  //admissible. It should be octile instead
  val heuristic = diagonalMovement match {
    case DiagonalMovement.Never =>
      desiredHeuristic getOrElse manhattan _
    case _ =>
      desiredHeuristic getOrElse octile _
  }

  import math.abs

  /**
    * Find and return the the path.
    *
    * @return Path The path, including both start and
    *         end positions.
    */
  override def findPath(startX: Int, startY: Int, endX: Int, endY: Int, grid: Grid): Path = {
    val closed = mutable.Set.empty[Node]

    val openList = new mutable.PriorityQueue[AStarInfo]()


    // push the start node into the open list
    val startNode = grid.nodeAt(startX, startY).get
    val startInfo = AStarInfo(startNode, f = Some(0), g = Some(0))

    val endNode = AStarInfo(grid.nodeAt(endX, endY).get)

    openList += startInfo

    var parents = Map.empty[Node,Node]

    while (openList.nonEmpty) {
      // pop the position of node which has the minimum `f` value.
      val info = openList.dequeue()
      val node = info.node
      closed += node

      // if reached the end position, construct the path and return it
      if (node.equals(endNode.node))
        return backtrace(endNode.node, parents)


      // get neighbors of the current node
      val neighbors = grid.getNeighbors(node, diagonalMovement)

      for {i <- neighbors.indices} {
        val neighbor = neighbors(i)
        val neighborIsClosed = closed.contains(neighbor)

        if (!neighborIsClosed) {
          val x = neighbor.x
          val y = neighbor.y

          // get the distance between current node and the neighbor
          // and calculate the next g score
          val ng = info.g.getOrElse(0d) + (if (x - node.x == 0 || y - node.y == 0) 1 else Sqrt2)

          val openSet = openList.toSet
          lazy val neighborInfo: Option[AStarInfo] = openSet.find(_.node.equals(neighbor))
          def neighborIsOpened: Boolean = neighborInfo.nonEmpty
          val neighborG = neighborInfo.flatMap(_.g).getOrElse(0d)
          // check if the neighbor has not been inspected yet, or
          // can be reached with smaller cost from the current node
          if (!neighborIsOpened || ng < neighborG) {

            val newNeighborG: Double = ng
            val newNeighborH: Double = {
              val nh = neighborInfo.flatMap(_.h).getOrElse(0d)
              if (nh == 0)
                weight * heuristic(abs(x - endX), abs(y - endY))
              else
                nh
            }

            val newNeighborF: Double = newNeighborG + newNeighborH

            parents = parents + (neighbor -> node)

            val newNeighborInfo = AStarInfo(
              neighbor,
              g = Some(newNeighborG),
              h = Some(newNeighborH),
              f = Some(newNeighborF)
            )

            if (!neighborIsOpened)
              openList += newNeighborInfo
            else {
              // the neighbor can be reached with smaller cost.
              // Since its f value has been updated, we have to
              // update its position in the open list
              val all = openList.dequeueAll
              val withNeighborUpdated = all.map {
                case i0 if i0.node.equals(neighbor) =>
                  newNeighborInfo
                case other => other
              }

              openList ++= withNeighborUpdated
            }

          }


        }
      }
    }

    // failed to find path
    IndexedSeq()
  }
}
