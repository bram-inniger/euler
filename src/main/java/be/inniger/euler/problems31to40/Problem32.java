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
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.DigitPermutationGenerator;
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
   * The idea is simple: generate all possible 9 digit permutations and check if the permutation can be formed in a way to create a valid product.
   * As we start by using only the permutations, and nothing else, we know a priori that whatever our findings, it will be a pandigital set of multiplicand, multiplier and product.
   * Now simply add up all the (unique) products so far and we have our answer.
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
   * The actual solving implementation.
   *
   * A first essential observation to take into account:
   * if we have 9 digits to divide over "a * b = c", then the #c (nr. of digits of "c") has to be equal to or larger than #a.
   * Thus "a" cannot have more than 4 digits in any case.
   * This information is captured in the variable "longestL", and used in the outer-most for-loop.
   * ==> #a <= 4
   *
   * Secondly, say both a and b have 2 digits, how many digits does c then have?
   * Best case a=10 and b=10, meaning c=100 -> 3 digits
   * Worst case a=99 and b=99, thus c=9801 -> 4 digits
   *
   * This means that #c = #a + #b (worst case) OR #a + #b - 1 (best case).
   * Combined with the fact above that #c >= #a, we can say that
   *      #c >= #a + #b -1     AND    #c <= #a + #b
   *      #a + #b + #c = 9
   *      #c >= #a
   *
   * Solving for #b this gives us: (this is expressed in the inner for-loop)
   * ==> #b <= 9/2 - #a + 1
   *
   *
   * Simply take every permutation, and split it up over every possible combination of "a" and "b", checking if what is left of the digits equals "a * b".
   * The actual checking of all combinations of "a" and "b" follows the constraints defined above.
   *
   * @return A Collection containing all unique 9 digits pandigital products.
   */
  @NotNull
  private Collection<Long> determineProducts() {
    int longestL = N / 2;
    Set<Long> products = new HashSet<>();                     // Cheap trick to get free de-duplication (we want to count every product only once)
    DigitPermutationGenerator gen = new DigitPermutationGenerator(1, N);

    while (gen.hasNext()) {                                   // Loop over every single permutation
      long perm = gen.next();

      for (int i = 1; i <= longestL; i++) {
        long multiplicand = Maths.getPart(perm, 0, i, N);     // Check if the first digit, the first 2, the first 3 or the first 4 digits generate a valid identity

        for (int j = 1; j <= longestL-i+1; j++) {
          long multiplier = Maths.getPart(perm, i, i+j, N);   // Do a similar check for all multipliers of different possible lengths
          long prod = multiplicand * multiplier;

          if (prod == Maths.getPart(perm, i+j, N, N)) {       // If the leftover digits form the product of the previous 2 we know we found a pandigital product
            products.add(prod);
          }
        }
      }
    }

    return products;
  }
}
