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

package be.inniger.euler.problems41to50;

import java.util.stream.LongStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem48 implements Problem {

  private static final int NR_DIGITS = 10;
  private static final int MAX_NR = 1000;

  private final long mask;  // Only care about the last n digits, using this mask


  /**
   * Constructor to set up the mask of appropriate length.
   */
  public Problem48() {
    mask = Maths.pow(10, NR_DIGITS);
  }

  /**
   * To solve: understand that in the end we will do a sum of a bunch of numbers and we only care about the final 10 digits.
   * So simply calculate separately 1^1 and 2^2 and ... and 1000^1000, only keeping the last 10 digits.
   * Then start summing them all, all the while also only keeping track of the last 10 digits.
   * How to only keep 10 digits: calculate the numbers module with 10^10 (mask) keeps only the last 10 digits of the number.
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        LongStream
            .rangeClosed(1L, MAX_NR)                // From 1 to 1000
            .map(this::calculateAddition)           // Map 1, 2, ..., 1000 to 1^1, 2^2, ..., 1000^1000
            .reduce(0L, (a, b) -> (a + b) % mask);  // Sum all of the above, always only keeping the last 10 digits (via "% mask")
  }

  /**
   * Get the last 10 digits of a particular term.
   * E.g. 42 -> 42^42
   * To do this simply multiple the starting value 1 with the number 42, for 42 times, always only keeping the last 10 digits.
   *
   * @param num The number to calculate num^num of
   * @return The last 10 digits of num^num
   */
  private long calculateAddition(long num) {
    return LongStream
        .generate(() -> num)                        // The multiplicand is always 42 (num)
        .limit(num)                                 // And this needs to be done 42 times (num)
        .reduce(1L, (a, b) -> (a * b) % mask);      // Multiply 1 by num a total of num times, only keeping track of the last 10 digits (via the "% mask" operation)
  }
}
