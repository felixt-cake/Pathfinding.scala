package org.terkwood.pathfinding.core

import org.scalatest.{FlatSpec, MustMatchers}
import org.terkwood.pathfinding.core.models.Point

class UtilitySpec extends FlatSpec with MustMatchers {
  "backtrace" must "return the path based on nodes' parents" ignore {
    ???
  }

  "interpolate" must "return the interpolated path" in {
    interpolate(0, 1, 0, 4) mustEqual
      IndexedSeq(
        Point(0, 1), Point(0, 2),
        Point(0, 3), Point(0, 4))
  }

  "expandPath" must "return an empty seq given an empty seq" ignore {
    expandPath(IndexedSeq()) mustEqual IndexedSeq()
  }

  ignore must "return the expanded path" in {
    expandPath(
      IndexedSeq(Point(0, 1), Point(0, 4))) mustEqual
      IndexedSeq(
        Point(0, 1), Point(0, 2),
        Point(0, 3), Point(0, 4))


    expandPath(IndexedSeq(
      Point(0, 1), Point(0, 4), Point(2, 6)
    )) mustEqual IndexedSeq(
      Point(0, 1), Point(0, 2), Point(0, 3),
      Point(0, 4), Point(1, 5), Point(2, 6)
    )
  }

  "compressPath" must "return the original path if it is too short to compress" ignore {
    compressPath(IndexedSeq()) mustEqual IndexedSeq()
  }

  ignore must "return a compressed path" in {
    compressPath(IndexedSeq(
      Point(0, 1), Point(0, 2),
      Point(0, 3), Point(0, 4)
    )) mustEqual IndexedSeq(
      Point(0, 1), Point(0, 4)
    )

    compressPath(IndexedSeq(
      Point(0, 1), Point(0, 2),
      Point(0, 3), Point(0, 4),
      Point(1, 5), Point(2, 6)
    )) mustEqual IndexedSeq(
      Point(0, 1), Point(0, 4),
      Point(2, 6)
    )
  }
}
