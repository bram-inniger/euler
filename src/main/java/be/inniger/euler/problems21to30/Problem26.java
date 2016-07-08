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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem26 implements Problem {

  private static final int LARGEST_DENOM = 1000 - 1;

  /**
   * One point to consider before diving in: numbers of this format have 2 parts: 0.xxx(yyy), where xxx denotes the fixed non-recurring part, and (yyy) the recurring fraction.
   * The fixed part originates from the factors 2 and 5 within the divisor:
   * E.g.   1/3 = 0.(3)
   *        1/6 = 0.1(6)
   * As we are only interested in the recurring cycle we need to first remove all factors 2 and 5 from the divisor.
   * Moreso, as these factors do not influence the repeating cycle length in any way we do not need to perform any further calculations after getting our answer.
   *
   * How to calculate digit by digit the division (taking 1/7 as an example):
   * 1. Take the current value of the numerator and mulitply it by 10 -> 1 * 10 = 10
   * 2. Perform the division, and take note of the remainder          -> 10/7 = 1 + 3/7
   * 3. This means our first digit of the cycle equals "1"
   * 4. Repeat step nr. 1 with the previous remainder as numerator    -> 3 * 10 = 30
   * 5. Repeat step nr. 2                                             -> 30/7 = 4 + 2/7
   * 6. The next digit of the cycle is thus 2, again repeat the steps above
   * 7. Eventually you will get a remainder that was already handled ("1" in this case), now you know one period of the cycle has ended and another one has started,
   *    as starting from 1, you will perform 10/7, giving a remainder of 3, which as we already know results in the next remainder of 2 ...
   *
   * A second point to consider here, following the algorithm above, is that a number (1 / x) cannot have a cycle larger then x.
   * This is because all of the potential remainders have to be smaller than x, as "a/b = c + d/b, with d<b"
   * In practice this means that we will start from "1/LARGEST_DENOM", going down to "1/1", tracking the largest recurring cycle size so far.
   * If ever the cycle size becomes larger than the current number under investigation we know we can already stop and have found the answer.
   */
  @NotNull
  @Override
  public String solve() {
    int largestCycleLength = -1;
    int largestCycleDivisor = -1;

    for (int i = LARGEST_DENOM; i > 0 ; i--) {
      if (i < largestCycleLength) { // No use continuing, see second point above
        break;
      }

      int cycleLength = calculateCycleLength(i);
      if (cycleLength > largestCycleLength) {
        largestCycleLength = cycleLength;
        largestCycleDivisor = i;
      }
    }

    return "" + largestCycleDivisor;
  }

  /**
   * Implementation of the algorithm above.
   * First remove the factors supplying the fixed part of the fraction, then check if there even is anything left to do.
   * Secondly keep finding the next cycle remainder and stop once you come across the first discovered remainder.
   * (As you now know the cycle will repeat itself)
   *
   * @param num The number we want to inspect the cycle length of
   * @return The cycle length
   */
  private int calculateCycleLength(int num) {
    num = removeFactors(num, 2, 5); // Remove all factors 2 and 5
    if (num == 1) { // Check if any recurring cycle even exists (if there was a factor there different from 2 and 5)
      return 0;
    }

    int length = 1; // At least 1
    int firstRemainder = 10 % num;
    int remainder = firstRemainder;

    while ((remainder = ((10 * remainder) % num)) != firstRemainder) {
      length++;
    }

    return length;
  }

  /**
   * Removes the specified factors from the number.
   *
   * @param num The number to remove factors from
   * @param factors Vararg amount of factors to remove
   * @return The results of removing the specified factors
   */
  private int removeFactors(int num, int... factors) {
    for (int factor : factors) {
      while (num % factor == 0) {
        num /= factor;
      }
    }

    return num;
  }
}
