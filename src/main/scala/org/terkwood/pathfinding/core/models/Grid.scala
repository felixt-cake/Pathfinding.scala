package org.terkwood.pathfinding.core.models

import org.terkwood.pathfinding.core.models.DiagonalMovement.DiagonalMovementOption

import scala.collection.mutable.ListBuffer
import scala.util.Try

/** A 2-dimensional coordinate grid containing
  * nodes which are either walkable, or not walkable.
  *
  * Serves as encapsulation for the layout of nodes.
  **/
trait Grid {
  /** The number of columns of the grid. */
  val width: Int
  /** The number of rows of the grid */
  val height: Int

  /** A boolean matrix representing the walkable
    * status of the nodes (false for walkable). */
  protected val matrix: IndexedSeq[IndexedSeq[WalkableStatus]]

  lazy val nodes: IndexedSeq[IndexedSeq[Node]] =
    buildNodes(width, height, matrix)

  /**
    * Get the node at the given coordinates.
    * Will hold an exception if given a coordinate out of bounds.
    *
    * @param x x coordinate: column
    * @param y y coordinate: row
    * @return
    */
  def nodeAt(x: Int, y: Int): Try[Node] = Try {
    nodes(y)(x)
  }

  /**
    * Determine whether the node at the given position is walkable.
    * (Also returns false if the position is outside the grid.)
    *
    * @param x - x coordinate of the node
    * @param y - y coordinate of the node
    * @return - Whether this node can be walked through
    */
  def isWalkableAt(x: Int, y: Int): Boolean =
    ((x, y) isInside this) && this.nodes(y)(x).walkable

  /**
    * Return a copy of the grid, with the node on the given position
    * updated with a new value for 'walkable'
    *
    * NOTE: does nothing if the coordinate is not inside the grid.
    *
    * @param x        - x coordinate of the node
    * @param y        - y coordinate of the node
    * @param walkable - can this node be walked on?
    * @return void
    */
  def withWalkableAt(x: Int, y: Int, walkable: Boolean): Grid = {
    val newNodes = for {i <- 0 until height} yield
      for {j <- 0 until width} yield
        if (x == j && y == i)
          Node(x, y, walkable)
        else this.nodeAt(j, i).get

    Grid(newNodes, width, height)
  }


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
  def getNeighbors(node: Node, diagonalMovement: DiagonalMovementOption): IndexedSeq[Node] = {
    val x = node.x
    val y = node.y
    val neighbors = ListBuffer.empty[Node]

    // ↑
    val s0 = this.isWalkableAt(x, y - 1)
    if (s0) neighbors += nodes(y - 1)(x)

    // →
    val s1 = this.isWalkableAt(x + 1, y)
    if (s1) neighbors += nodes(y)(x + 1)

    // ↓
    val s2 = this.isWalkableAt(x, y + 1)
    if (s2) neighbors += nodes(y + 1)(x)

    // ←
    val s3 = this.isWalkableAt(x - 1, y)
    if (s3) neighbors += nodes(y)(x - 1)

    diagonalMovement match {
      case DiagonalMovement.Never =>
        neighbors.toIndexedSeq

      case _ =>
        val (d0, d1, d2, d3) = diagonalMovement match {
          case DiagonalMovement.OnlyWhenNoObstacles =>
            (s3 && s0,
              s0 && s1,
              s1 && s2,
              s2 && s3)

          case DiagonalMovement.IfAtMostOneObstacle =>
            (s3 || s0,
              s0 || s1,
              s1 || s2,
              s2 || s3)

          case DiagonalMovement.Always =>
            (true, true, true, true)

          case _ => throw new Exception("not reachable")
        }

        // ↖
        if (d0 && this.isWalkableAt(x - 1, y - 1))
          neighbors += nodes(y - 1)(x - 1)

        // ↗
        if (d1 && this.isWalkableAt(x + 1, y - 1))
          neighbors += nodes(y - 1)(x + 1)

        // ↘
        if (d2 && this.isWalkableAt(x + 1, y + 1))
          neighbors += nodes(y + 1)(x + 1)

        // ↙
        if (d3 && this.isWalkableAt(x - 1, y + 1))
          neighbors += nodes(y + 1)(x - 1)

        neighbors.toIndexedSeq
    }
  }

  /** Create a copy of the grid, potentially
    * with altering a given node.
    *
    * @return
    */
  override def clone(): Grid = Grid(nodes, width, height)

  /**
    * Build and return the nodes.
    *
    * @param width  width
    * @param height height
    * @param matrix A Boolean matrix representing
    *               the walkable status of the nodes.
    * @return
    */
  private def buildNodes(width: Int,
                         height: Int,
                         matrix: IndexedSeq[IndexedSeq[WalkableStatus]]): IndexedSeq[IndexedSeq[Node]] = {

    if (matrix.nonEmpty)
      assert(matrix.length == height && matrix.head.length == width,
        "Matrix size does not fit")

    for {
      i <- 0 until height
    } yield for {
      j <- 0 until width
    } yield
      Node(j, i, walkable =
        if (matrix.isEmpty)
          false
        else matrix(i)(j) match {
          case Walk => true
          case NoWalk => false
        })
  }

}


object Grid {

  /**
    * The Grid class, which serves as the encapsulation
    * of the layout of the nodes.
    *
    * @param width  Number of columns in the grid
    * @param height Number of rows in the grid
    * @param matrix A boolean matrix representing the walkable
    *               status of the nodes (false for walkable).
    *               If the matrix is not supplied, all the nodes
    *               will be walkable.
    * @return
    */
  def apply(width: Int, height: Int, matrix: IndexedSeq[IndexedSeq[WalkableStatus]] = IndexedSeq.empty): Grid = {
    val (w, h, m) = (width, height, matrix)
    new Grid {
      override val matrix: IndexedSeq[IndexedSeq[WalkableStatus]] = m
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
  def apply(matrix: IndexedSeq[IndexedSeq[WalkableStatus]]): Grid = {
    val m = matrix
    new Grid {
      override val matrix: IndexedSeq[IndexedSeq[WalkableStatus]] = m
      override val height: Int = matrix.length
      override val width: Int = matrix.head.length

      assert(matrix.nonEmpty && matrix.head.nonEmpty,
        "Please supply a matrix which has data in both dimensions")

    }
  }

  /** Used by [withWalkableAt] and [clone] */
  protected def apply(nodes: IndexedSeq[IndexedSeq[Node]], width: Int, height: Int): Grid = {
    val origWidth = width
    val origHeight = height
    val newNodes = nodes
    new Grid {
      override val width: Int = origWidth
      /** won't be used, at this point */
      override val matrix: IndexedSeq[IndexedSeq[WalkableStatus]] = IndexedSeq()
      override val height: Int = origHeight
      override lazy val nodes = newNodes
    }
  }
}
