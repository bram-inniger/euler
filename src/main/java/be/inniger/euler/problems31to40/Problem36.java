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

import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * The decimal number, 585 = 1001001001_2 (binary), is palindromic in both bases.
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem36 implements Problem {

  @SuppressWarnings("PointlessArithmeticExpression")
  private static final int LIMIT = 1 * 1000 * 1000;
  private static final int BASE_TEN = 10;
  private static final int BASE_TWO = 2;

  /**
   * Simple solution: Stream all numbers up to 1 million, filter on the ones that are palindromic in both bases, and sum it up.
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        IntStream
            .range(1, LIMIT)
            .filter(this::isPalindromicInBothBases)
            .sum();
  }

  /**
   * Check if the number is palindromic in both base 10 and base 2
   *
   * @param num The number to check
   * @return True if the number is palindromic both in base 10 and base 2
   */
  private boolean isPalindromicInBothBases(int num) {
    return isPalindromic(Integer.toString(num, BASE_TEN)) &&
           isPalindromic(Integer.toString(num, BASE_TWO));
  }

  /**
   * Check if a given number is palindromic in any arbitrary base.
   * This works as the input argument is in String format, in any given base.
   *
   * @param num The number to check
   * @return True if the number is palindromic
   */
  private boolean isPalindromic(String num) {
    char[] chars = num.toCharArray();
    int length = chars.length;

    for (int i = 0; i < length/2; i++) {
      if (chars[i] != chars[length-1-i]) {
        return false;
      }
    }

    return true;
  }
}
