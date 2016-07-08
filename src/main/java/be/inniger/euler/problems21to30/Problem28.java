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

package be.inniger.euler.problems21to30;

import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem28 implements Problem {

  @SuppressWarnings("FieldCanBeLocal")
  private final int SPIRAL_SIZE = 1001;

  /**
   * A 5*5 results in the following patters on the diagonal (working square per square):
   * Most central square:   1           (distance 0 from center)
   * One square further:    3  5  7  9  (distance 1 from center)
   * One square further:    13 17 21 25 (distance 2 from center)
   *
   * Thus a 5*5 spiral creates 3 squares total: ((spiral size - 1) / 2 + 1) = ((5 - 1) / 2 + 1) = 3.
   * Likewise a 1001*1001 spiral will create 501 squares total: ((1001) / 2 + 1) = 501.
   *
   * The pattern to discover here is that the largest number (1, 9, 25, ...) are quadratic numbers.
   * Their roots are equal to "2 times the distance from center plus 1", meaning the next root will be 7, resulting in the next square 49.
   * -> distance 0 = 0*2 + 1 = 1, 1*1 = 1
   * -> distance 1 = 1*2 + 1 = 3, 3*3 = 9
   * -> distance 2 = 2*2 + 1 = 5, 5*5 = 25
   *
   * Starting from this quadratic number we can follow another pattern to count down the other 3 diagonal numbers.
   * Take the starting quadratic number, and keep subtracting "distance from center times 2"
   * E.g. 25 is on distance from center 2. The previous number is this 25 - 2*2 = 21, next one 21 - 2*2 = 17, ...
   *
   * Putting it all together, for any square at distance "x" from the center, the sum of the diagonal numbers can be calculated as:
   * Quadratic number "q" = (2x+1)^2 = 4x^2 + 4x + 1
   * Sum of all 4 numbers = (q) + (q -2x ) + (q - 2x -2x) + (q -2x -2x -2x) = 4q - 12x = 4*(4x^2 + 4x + 1) - 12x = 16x^2 + 4x + 4
   *
   * Simply let x grow from 1 to max-square-size, and add the value "1" for the center value at square distance 0, and we are done!
   */
  @NotNull
  @Override
  public String solve() {
    return "" + (
        IntStream
            .range(1, (SPIRAL_SIZE-1) / 2 + 1) // Start from 1, so do not count the special "square" of size 1 at distance 0
            .map(x -> 16*x*x + 4*x + 4)
            .sum()
                   + 1); // For the size 1 "square" at distance 0
  }
}
