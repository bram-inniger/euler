package be.inniger.euler.problems11to20;

import java.math.BigInteger;

import static be.inniger.euler.util.Math.factorial;

/**
 * Lattice paths
 * <p>
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?
 */
public class Problem15 {

  private static final int GRID_DIMENSION = 20;

  /*
   * Imagine a 4 * 4 grid (GRID_DIMENSION = 4) with x being the endpoint from which we want to know the amount of routes reaching it
   * . . . .
   * . . . .
   * . . . .
   * . . . x
   *
   * Start by assigning one to the origin (top left), as this is where all routes start, and it maps onto itself
   * The two elements directly rightwards and downwards now can also only be reaching 1 way, that way being directly from the origin point
   *
   * 1 1 . .
   * 1 . . .
   * . . . .
   * . . . x
   *
   * Continue the line of thought:
   * * on the top row, continuing on the right, again only 1 routed can pass here, the one coming directly from its left neighbour, which itself has only 1 route passing
   * * same reasoning for continuing downwards on the leftmost side, only 1 possible path here
   * * more interesting in the newly discovered middle element, it can be reached from above (with 1 possible route) and from the left (with also 1 route) meaning 2 routes pass here (1 + 1)
   *
   * 1 1 1 .
   * 1 2 . .
   * 1 . . .
   * . . . x
   *
   * Similar as last time, the element next to the one with value "2" and below the one with value "1", logically has 3 paths passing through it, the 2 paths coming through its right, and the one path above
   * Apply this simple logic of summing the left and upper neighbours (if any) to discover a new set of values
   *
   * 1 1 1 1
   * 1 2 3 .
   * 1 3 . .
   * 1 . . x
   *
   * A pattern emerges! That of Pascal's triangle! https://en.wikipedia.org/wiki/Pascal%27s_triangle
   * Drawing out the full triangle that contains "x" (marked with parenthesis)
   *
   *   1    1    1    1    1    1    1
   *   1    2    3    4    5    6
   *   1    3    6    10   15
   *   1    4    10  (20)
   *   1    5    15
   *   1    6
   *   1
   *
   * This reduces the problem to the simpler combinatorial equation below.
   * On this case, find the row "x" is on and mark it as "n", then find its position on said row as "k"
   *
   * In the GRID_DIMENSION = 20 case n = 2*20 = 40 and k = 20
   */
  public long solve() {
    return nChooseK(2 * GRID_DIMENSION, GRID_DIMENSION)
        .longValueExact();
  }

  /*
   * Calculate the number of combinations when picking k elements from n total.
   *
   * C(n, k) = n! / (k! * (n - k)!)
   */
  private BigInteger nChooseK(int n, int k) {
    return factorial(n).divide(
        factorial(k).multiply(factorial(n - k)));
  }
}
