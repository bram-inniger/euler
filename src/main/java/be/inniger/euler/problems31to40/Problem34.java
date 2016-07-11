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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem34 implements Problem {

  /**
   * Solving this issue is twofold:
   * 1. Determine an upper bound to the problem-space.
   * 2. Iterate over the bounded problem space, finding numbers that comply with the rule stated in the problem.
   */
  @NotNull
  @Override
  public String solve() {
    List<Integer> facts = IntStream              // Build a cache of the factorials of the digits 0 up to 9 included, as these will be used a lot
        .rangeClosed(0, 9)
        .map(Maths::calculateSmallFactorial)
        .boxed()
        .collect(Collectors.toList());

    int upperBound = determineUpperBound();      // Get the upper bound, binding the problem space
    int totalSum = 0;

    for (int i = 10; i < upperBound; i++) {
      int sum = Maths.getDigits(i)               // Calculate the sum of the factorials of the digits
          .stream()
          .mapToInt(facts::get)
          .sum();

      if (sum == i) {                            // Compare the sum to the original number
        totalSum += sum;
      }
    }

    return "" + totalSum;
  }

  /**
   * As numbers, per digit, are growing at a rate "~ 10^n", and the max possible digit sum is growing at a rate "~ n*9!",
   * it is clear to see at some point "n" will be too large for the digit sum to keep up with.
   * This allows us to be certain there indeed has to exist an upper bound.
   *
   * The easiest way to determine it, it simply by iterating the "lowest case" LHS (the number) vs the "highest case" RHS (digit sum).
   * Once the LHS is larger than RHS for a given "n" we know we found our upper bound.
   *
   * E.g.: 10^0 < 1*9!
   *       10^1 < 2*9!
   *       ...
   *       10^n < (n+1)*9!
   *
   * @return The upper bound, binding the problem space
   */
  private int determineUpperBound() {
    int n = 0;
    int fact9 = Maths.calculateSmallFactorial(9);

    while (Maths.pow(10, n) < (n+1)*fact9) {
      n++;
    }

    return (n+1)*fact9;
  }
}
