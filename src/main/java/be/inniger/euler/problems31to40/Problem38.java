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

package be.inniger.euler.problems31to40;

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem38 implements Problem {

  private static final int NR_DIGITS = 9;
  private static final int N_BOUND = 8;

  /**
   * The key to solving this issue is simply bounding the problem space as small as possible.
   * <p>
   * <p>
   * Bound 1, binding n
   * <p>
   * If x is the number we will compute the 1 to n concatenated product of, then the following is true:
   * Should x be the smallest possible integer (x = 1), then n = 9 is a trivial solution: 123456789
   * It is easy to see now that for any x larger than 1 any n value equal to 9 will produce more than the allowed 9 digits.
   * (e.g. x = 2 would result in 24681012141618 -> more than 9 digits, so by definition cannot be 1 to 9 pandigital.)
   * <p>
   * The conclusion to take here is that n is bound from 2 to 8 (inclusive) for values x larger than 1.
   * <p>
   * <p>
   * Bound 2, binding x
   * <p>
   * If we concatenate the multiples of x with 1 to n, then the answer will have AT LEAST nrDigits(x) * n digits.
   * E.g. if x is the number 1234 (4 digits) and n equals 2, then the answer has to be at least 4*2 = 8 digits long.
   * Indeed, in this case it would be 12342468.
   * <p>
   * Reversing the formulate above this binds the max nr of digits of x to "nr of digits total (9) / n" (rounded down).
   * Say nr of digits x is 4, then we can say x is bound from 1 to 10^4 = 10000 (exclusive) or 9999 inclusive.
   * <p>
   * <p>
   * Using these 2 bounds the total problem space is reduced to about (give or take, on average) 8 * 1500 = 12k possibilities.
   * A simple (smartly bounded) brute force of all of these should yield a solution in an acceptable amount of time.
   */
  @NotNull
  @Override
  public String solve() {
    long maxProduct = 123456789L;                   // For x = 1 and n = 9, the trivial solution

    for (int n = 2; n <= N_BOUND; n++) {
      long xBound = Maths.pow(10, NR_DIGITS / n);   // Given that n >= 2, and proven that n <= 8

      for (int x = 2; x < xBound; x++) {            // If x equals 1, then n has to be 9, this solution is already recorded, the bounded value is also proven above
        long product = calculateConcatenatedProduct(x, n);

        if (product > maxProduct && Maths.isPandigital(product)) {
          maxProduct = product;
        }
      }
    }

    return "" + maxProduct;
  }

  /**
   * Calculate the concatenated product of a number x with the numbers 1 to n (inclusive).
   *
   * @param x Base number for the concatenation
   * @param n Products from 1 to n inclusive
   * @return The concatenated product
   */
  private long calculateConcatenatedProduct(int x, int n) {
    long concatenatedProduct = x;

    for (int i = 2; i <= n; i++) {
      concatenatedProduct = Maths.concatenate(concatenatedProduct, i * x);
    }

    return concatenatedProduct;
  }
}
