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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem32 implements Problem {

  private static final int N = 9;

  /**
   * Implemented a rather un-elegant method:
   * Just try all multiplicand / multiplies combinations, then verify if the concatenated identity is pandigital or not.
   * Once again we will store the results in a HashSet to get "free" de-duplication.
   *
   * A neater solution could be:
   * 1. create a collection holding all digits from 1 to 9.
   * 2. try all permutations of this connection over both multiplicand and multiplier
   * 3. simply check if the product contains the digits not yet permuted over, if they match then the number is by definition pandigital
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        determineProducts()
            .stream()
            .parallel()
            .mapToLong(Long::longValue)
            .sum();
  }

  /**
   * There are three optimisations to use:
   * 1. Say "a * b = c", and all the digits from 1 to 9 are in play once and once only.
   *    Then if a contains 5 digits, b still has to contain at least 1, leaving 3 digits for the product.
   *    A product of a 5 digit number and a 1 digit number cannot have less than 5 digits itself.
   *    So it is easy to see that both a and b have to be between 1 and 4 digits long.
   * 2. Following the above a and b have to be between 1 (the lowest possible value) and 9876 (the highest possible value without repeating digits, that is 4 digits long)
   * 3. Combining the above 2 it pays up to pre-compute all the integers between 1 and 9876 that do not contain any repeating digits, this allows to not even perform the relatively expensive "isPandigital" check in many cases
   *
   * @return A Collection containing all of the products found
   */
  @NotNull
  private Collection<Long> determineProducts() {
    Set<Long> products = new HashSet<>();
    Collection<Long> candidates = getPandigitalCandidates();

    for (long multiplicand : candidates) {
      for (long multiplier :candidates) {
        long product = multiplicand * multiplier;
        long total = Long.valueOf("" + multiplicand + multiplier + product);
        if (candidates.contains(product) && Maths.isPandigital(total, N)) {
          products.add(product);
          Maths.isPandigital(total, N);
        }
      }
    }

    return products;
  }

  /**
   * Cache all of the potential values to check.
   * These all share the same property: they have no repeating digits.
   *
   * @return A collection of potential candidates
   */
  private Collection<Long> getPandigitalCandidates() {
    return LongStream
        .rangeClosed(1, 9876)
        .parallel()
        .filter(this::isCandidate)
        .boxed()
        .collect(Collectors.toSet());
  }

  /**
   * Check if a number has repeating digits or not (a.k.a. is a candidate or not).
   *
   * @param num The number to check
   * @return True if the number is a potential candidate
   */
  private boolean isCandidate(long num) {
    Set<Long> digits = new HashSet<>();
    int count = 0;
    while (num > 0) {
      digits.add(num % 10);
      num /= 10;
      count++;
    }

    return count == digits.size();
  }
}
