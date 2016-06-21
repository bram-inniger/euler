/*
 * Project Euler solution repository
 * Copyright (C) 2016 Bram Inniger
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package be.inniger.euler.problems11to20;

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem15 implements Problem {

  private static final int GRID_SIZE = 20 + 1; // The +1 is needed, if GRID_SIZE = 2, then potential locations are 0, 1 and 2

  /**
   * The general idea on how to solve this:
   * 1. Create a GRID_SIZE by GRID_SIZE 2D array, the intention:
   *    fill every element with the nr of possible paths starting from that element.
   * 2. Start at the very end point (20,20)
   * 3. Keep on the bottom row, and start going left to right, filling in the spots until you hit (0, 20)
   *    (19, 20) has 1 way of going to (20, 20), so it gets value one.
   *    (18, 20) can only go to (19, 20), so the number of possible paths #(18, 20) = #(19, 20) = 1
   *    ...
   * 4. Now add a row above:
   *    (20, 19) only has 1 way of going to (20, 20), so #(20, 19) = 1
   *    (19, 19) on the other hand can go via (19, 20) and (20, 19)
   *    We can thus say #(19, 19) = #(19, 20) + #(20, 19) = 1 + 1 = 2
   * 5. Keep adding rows like this until the point (0, 0) is also filled, this is our answer.
   *
   * Calculating like this only requires a nested for-loop and basic addition mathematics,
   * evading costly recursive calls as would be the case in the naive solution.
   *
   * @return The solution
   */
  @NotNull
  @Override
  public String solve() {
    long[][] grid = new long[GRID_SIZE][GRID_SIZE];

    // For simplicity in code we will set all the values of the far right column already to 1 (their eventual value anyway).
    // We will do the same thing for the most bottom row.
    // Because of this the actual code does not match a 100% to the explanation above.
    // However, the idea behind it is still correct, and is still the one followed.
    for (int i = 0; i < GRID_SIZE; i++) {
      grid[i][GRID_SIZE-1] = 1L;
      grid[GRID_SIZE-1][i] = 1L;
    }

    for (int row = GRID_SIZE-2; row >= 0; row--) {
      for (int col = GRID_SIZE-2; col >=0; col--) {
        grid[row][col] = grid[row+1][col] + grid[row][col+1];
      }
    }

    return "" + grid[0][0];
  }
}
