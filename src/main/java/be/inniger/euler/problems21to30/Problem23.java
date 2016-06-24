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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem23 implements Problem {

  private final static int LIMIT = 28123;

  @NotNull
  @Override
  public String solve() {
    long sum = 0L;
    Set<Integer> abundants = determineAbundantNumbers();

    for (int i = 1; i < LIMIT; i++) {
      boolean isWritable = false;

      for (int abundant : abundants) {
        if (abundants.contains(i-abundant)) {
          isWritable = true;
          break;
        }
      }

      if (!isWritable) {
        sum += i;
      }
    }

    return "" + sum;
  }

  /**
   * Get all of the abundant numbers up until the limit.
   *
   * @return A Set of all abundant numbers up until LIMIT
   */
  private Set<Integer> determineAbundantNumbers() {
    return IntStream.range(1, LIMIT)
        .filter(i -> Maths.sumProperDivisors(i) > i)
        .boxed()
        .collect(Collectors.toSet());
  }
}
