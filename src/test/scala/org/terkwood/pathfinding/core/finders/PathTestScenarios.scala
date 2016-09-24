package org.terkwood.pathfinding.core.finders

import org.terkwood.pathfinding.core.models.{WalkableStatus, _}

case class Scenario(id: Int,
                    startX: Int, startY: Int,
                    endX: Int, endY: Int,
                    matrix: IndexedSeq[IndexedSeq[WalkableStatus]],
                    expectedLength: Int)

trait PathTestScenarios {

  val PathTestScenarios = Seq(
    Scenario(
      id = 0,
      startX = 0,
      startY = 0,
      endX = 1,
      endY = 1,
      matrix = IndexedSeq(


        IndexedSeq(0, 0),
        IndexedSeq(1, 0))


        .toWalkable,

      expectedLength = 3),
    Scenario(
      id = 1,
      startX = 1,
      startY = 1,
      endX = 4,
      endY = 4,
      matrix = IndexedSeq(

        IndexedSeq(0, 0, 0, 0, 0),
        IndexedSeq(1, 0, 1, 1, 0),
        IndexedSeq(1, 0, 1, 0, 0),
        IndexedSeq(0, 1, 0, 0, 0),
        IndexedSeq(1, 0, 1, 1, 0),
        IndexedSeq(0, 0, 1, 0, 0))

        .toWalkable,
      expectedLength = 9
    ),
    Scenario(
      id = 2,
      startX = 0,
      startY = 3,
      endX = 3,
      endY = 3,
      matrix = IndexedSeq(

        IndexedSeq(0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 1, 1, 0),
        IndexedSeq(0, 0, 1, 0, 0),
        IndexedSeq(0, 0, 1, 0, 0),
        IndexedSeq(1, 0, 1, 1, 0),
        IndexedSeq(0, 0, 0, 0, 0))

        .toWalkable,
      expectedLength = 10
    ),
    Scenario(
      id = 3,
      startX = 4,
      startY = 4,
      endX = 19,
      endY = 19,
      matrix = IndexedSeq(

        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        IndexedSeq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).toWalkable,
      expectedLength = 31
    )
  )

}
