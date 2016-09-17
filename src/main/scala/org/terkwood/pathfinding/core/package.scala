package org.terkwood.pathfinding

package object core {

  /**
    * Determine whether the position is inside the grid.
    */
  implicit class pointIsInsideGrid(from: (Int, Int)) {
    def isInside(grid: Grid) =
      (from._1 >= 0 && from._1 < grid.width) &&
        (from._2 >= 0 && from._2 < grid.height)
  }

  implicit class intMatrixToWalkableStatus(from: Seq[Seq[Int]]) {
    def toWalkableStatusMatrix: IndexedSeq[IndexedSeq[WalkableStatus]] =
      from.toIndexedSeq.map {
        _.toIndexedSeq.map {
          WalkableStatus.from
        }
      }
  }

  implicit class booleanMatrixToWalkableStatus(from: Seq[Seq[Boolean]]) {
    def toWalkableStatusMatrix: IndexedSeq[IndexedSeq[WalkableStatus]] =
      from.toIndexedSeq.map {
        _.toIndexedSeq.map {
          WalkableStatus.from
        }
      }
  }

}
