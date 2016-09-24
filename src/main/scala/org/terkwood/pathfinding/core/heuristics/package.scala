package org.terkwood.pathfinding.core

/** A collection of heuristic functions */
package object heuristics {
  type Heuristic = (Double, Double) => Double

  /**
    * Manhattan distance.
    *
    * @param   dx - Difference in x.
    * @param   dy - Difference in y.
    * @return dx + dy
    */
  def manhattan(dx: Double, dy: Double): Double =
    dx + dy

  /**
    * Euclidean distance.
    *
    * @param   dx - Difference in x.
    * @param   dy - Difference in y.
    * @return  sqrt(dx * dx + dy * dy)
    */
  def euclidean(dx: Double, dy: Double): Double = {
    math.sqrt(dx * dx + dy * dy)
  }

  /**
    * Octile distance.
    *
    * @param   dx - Difference in x.
    * @param   dy - Difference in y.
    * @return sqrt(dx * dx + dy * dy) for grids
    */
  def octile(dx: Double, dy: Double): Double = {
    val F = math.sqrt(2) - 1

    if (dx < dy) F * dx + dy else F * dy + dx
  }

  /**
    * Chebyshev distance.
    *
    * @param   dx - Difference in x.
    * @param   dy - Difference in y.
    * @return max(dx, dy)
    */
  def chebyshev(dx: Double, dy: Double): Double =
    math.max(dx, dy)

}
