package org.terkwood.pathfinding.core.models

import org.scalatest.{FlatSpec, MustMatchers}

class WalkableStatusConversionSpec extends FlatSpec with MustMatchers {
  val Expected: IndexedSeq[IndexedSeq[WalkableStatus]] = IndexedSeq(
    IndexedSeq(NoWalk, Walk, Walk, NoWalk),
    IndexedSeq(Walk, NoWalk, Walk, Walk),
    IndexedSeq(Walk, NoWalk, Walk, Walk),
    IndexedSeq(Walk, Walk, Walk, Walk),
    IndexedSeq(NoWalk, Walk, Walk, NoWalk)
  )

  "The WalkableStatus conversion suite" must "allow conversion of Seq[Seq[Int]] to IndexedSeq[IndexedSeq[WalkableStatus]]" in {
    val matrix: Seq[Seq[Boolean]] = Seq(
      Seq(true, false, false, true),
      Seq(false, true, false, false),
      Seq(false, true, false, false),
      Seq(false, false, false, false),
      Seq(true, false, false, true)
    )

    matrix.toWalkable mustEqual Expected
  }

  it must "allow conversion of Seq[Seq[Boolean]] to IndexedSeq[IndexedSeq[WalkableStatus]]" in {
    val matrix: Seq[Seq[Boolean]] = Seq(
      Seq(true, false, false, true),
      Seq(false, true, false, false),
      Seq(false, true, false, false),
      Seq(false, false, false, false),
      Seq(true, false, false, true)
    )

     matrix.toWalkable mustEqual Expected
  }

}
