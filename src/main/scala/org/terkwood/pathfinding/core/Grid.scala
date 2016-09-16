package org.terkwood.pathfinding.core

object Grid {
  val Walkable = false
  val NotWalkable = true

  /**
    * The Grid class, which serves as the encapsulation
    * of the layout of the nodes.
    *
    * @param width  Number of columns in the grid
    * @param height Number of rows in the grid
    * @return
    */
  def apply(width: Int, height: Int) = {
    val (w, h) = (width, height)
    new Grid {
      override val matrix: IndexedSeq[IndexedSeq[Boolean]] = IndexedSeq.empty
      override val height: Int = h
      override val width: Int = w

      assert(width > 0 || height > 0,
        "Please use positive values for width & height")
    }
  }

  /**
    * The Grid class, which serves as the encapsulation of the layout of the nodes.
    *
    * @param matrix A boolean matrix representing the walkable
    *               status of the nodes (false for walkable).
    *               If the matrix is not supplied, all the nodes
    *               will be walkable.
    * @return
    */
  def apply(matrix: IndexedSeq[IndexedSeq[Boolean]]) = {
    val m = matrix
    new Grid {
      override val matrix: IndexedSeq[IndexedSeq[Boolean]] = m
      override val height: Int = matrix.length
      override val width: Int = matrix.head.length

      assert(matrix.nonEmpty && matrix.head.nonEmpty,
        "Please supply a matrix which has data in both dimensions")
    }
  }
}

trait Grid {
  /** The number of columns of the grid. */
  val width: Int
  /** The number of rows of the grid */
  val height: Int

  /** A boolean matrix representing the walkable
    * status of the nodes (false for walkable). */
  val matrix: IndexedSeq[IndexedSeq[Boolean]]

  lazy val nodes: IndexedSeq[IndexedSeq[Node]] =
    buildNodes(width, height, matrix)

  def getNodeAt(x: Int, y: Int): Node = nodes(y)(x)

  def isWalkableAt(x: Int, y: Int): Boolean = ???

  def isInside(x: Int, y: Int): Boolean = ???

  def withWalkableAt(x: Int, y: Int): Grid = ???

  /**
    * Get the neighbors of the given node.
    *
    * offsets      diagonalOffsets:
    * +---+---+---+    +---+---+---+
    * |   | 0 |   |    | 0 |   | 1 |
    * +---+---+---+    +---+---+---+
    * | 3 |   | 1 |    |   |   |   |
    * +---+---+---+    +---+---+---+
    * |   | 2 |   |    | 3 |   | 2 |
    * +---+---+---+    +---+---+---+
    *
    * When allowDiagonal is true, if offsets[i] is valid, then
    * diagonalOffsets(i) and
    * diagonalOffsets((i + 1) % 4) is valid.
    *
    * @param node             node
    * @param diagonalMovement diagonalMovement type
    * @return
    */
  def getNeighbors(node: Node, diagonalMovement: DiagonalMovement): Seq[Node] = ???

  /**
    * Get a clone of this grid.
    *
    * @return cloned grid
    */
  override def clone: Grid = ???

  private def buildNodes(w: Int,
                         h: Int,
                         m: IndexedSeq[IndexedSeq[Boolean]]): IndexedSeq[IndexedSeq[Node]] =
    ???
}
