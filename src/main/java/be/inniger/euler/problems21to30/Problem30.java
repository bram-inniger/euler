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
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * As 1 = 1^4 is not a sum it is not included.
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem30 implements Problem {

  private static final int POWER = 5;

  /**
   * The strategy to solve this one is simple:
   * 1. Determine the upper bound of numbers that can potentially be written as the sum of the fifth powers of their digits.
   * 2. Simply try every number starting from 10 (2 digits at least, otherwise it is no sum) up to the limit, and sum the numbers that conform.
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        IntStream
            .rangeClosed(10, calculateLimit())
            .filter(this::isSumOfPowers)
            .sum();
  }

  /**
   * Iterative way of finding the limit: at some point the number is too large to be able to be represented by the sum of the powers of its digits.
   * For example:
   *     The largest 2 digit number = 99
   *     Sum of the powers of digits = 9^5 + 9^5 = 118,098 > 99   ==> every 2 digit number is potentially expressed a sum of its digit's powers.
   *
   *     But: the largest 7 digit number = 9,999,999 (and the smallest 1,000,000)
   *     Sum of the powers of digits = 9^5 + 9^5 + ... + 9^5 = 7*9^5 = 413,343 < 1,000,000   ==> even the largest possible sum of powers, is smaller than the smallest possible 7-digit number.
   *     This means that we for sure cannot write any 7 digit number like this and we have already found a hard upper limit (though there is a better one).
   *
   * In general the following is true for finding the limit:
   *     Say "x" is the number of digits and "p" the power we want to raise them to, then we can say we have passed the upper limit if
   *          x * 9^p < 10^(x-1)
   *     The moment this becomes true we can put the upper limit at
   *          (x-1) * 9^p
   */
  private int calculateLimit() {
    int x = 1;
    while (x * Maths.pow(9, POWER) > Math.pow(10, x-1)) {
      x++;
    }

    return (x-1) * (int) Maths.pow(9, POWER);
  }

  /**
   * Stream over all separate digit, map to the power of the digit, sum, and finally compare to the original number.
   *
   * @param num The number to be tested
   * @return True if the number is equal to the sum of the powers of its digits
   */
  private boolean isSumOfPowers(int num) {
    int sumOfPowers = Maths.getDigits(num)
        .stream()
        .mapToInt(i -> (int) Maths.pow(i, POWER))
        .sum();
    
    return num == sumOfPowers;
  }
}
