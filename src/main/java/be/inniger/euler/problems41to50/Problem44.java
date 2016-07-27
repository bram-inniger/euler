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

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten pentagonal numbers are:
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 − 22 = 48, is not pentagonal.
 * Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and D = |Pk − Pj| is minimised; what is the value of D?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem44 implements Problem {

  /**
   * Reasonably simple solution:
   * Calculate (and cache) new and increasingly bigger pentagonal numbers, one by one.
   * Let use call the newly generated pentagonal number P1.
   *
   * P1 has the interesting property that it is larger than all other pentagonal numbers seen so far.
   * Now look at ALL the previously generated pentagonal numbers, one by one, call this P2.
   * P1-P2 will by definition always be a positive integer.
   *
   * Simply now check whether P1-P2 is indeed a pentagonal number, and then P1+P2.
   * If one is found return (check the note below).
   * If none is found then generate new now highest pentagonal number, and compare it to all of them below.
   *
   * To check the difference we can just do a Set#contains operation, which completes very fast for a HashSet ("O(1)" almost).
   * This works because the difference is always smaller than the currently generated pentagonal number, meaning that, if it is not found in the cache it is by definition not a pentagonal number.
   *
   * For the sum we cannot use this method, as that potentially pentagonal number has not yet been computed.
   * In this case fall back on the still reasonably fast this#isPentagonal method.
   *
   *
   * N.B.: Even though the calculated solution is the right one, this program is not "correct".
   * We are lucky that the first pair P1 and P2 that corresponds to the rule above is also the one with minimal D = P1-P2
   * A fully correct / proven solution is not hard to implement, but will run much longer, and is omitted here.
   *
   * The trick for the correct one is keeping track not only of the currently generated pentagonal number P1, but also of the previous P1 value.
   * The reasoning:
   * We need to minimise P1 - P2, with P1 being the larger one.
   * If P1 is the nth pentagonal number P_n, then the minimum difference achievable is with P2 being equal to P_n-1
   * We can also see that the D = P1-P2 becomes larger as index n increases.
   *
   * Instead of returning the different P1-P2 as we do before we simply need to store the minimum difference found so far.
   * If ever the current P1 minus the previous P1 exceeds the minimum difference found, then by definition no smaller difference will ever be found.
   * This leads directly from the fact that D = P_n - P_n-1 is always increasing when n increases.
   * When this happens simply return the now proven smallest difference recorded.
   */
  @NotNull
  @Override
  public String solve() {
    Set<Long> pentagonals = new HashSet<>();
    long n = 1L;                           // Starting value of n

    while (true) {
      long p1 = n * (3*n - 1) / 2;         // Formula described above to generate pentagonal numbers
      pentagonals.add(p1);                 // Cache the calculated value

      for (long p2 : pentagonals) {
        if (pentagonals.contains(p1-p2) && // Can use the cache for this
            isPentagonal(p1+p2)) {         // Can't use the cache for this
          return "" + (p1-p2);
        }
      }

      n++;                                 // Prepare the next iteration
    }
  }

  /**
   * Determine if a number is a pentagonal number.
   *
   * The strategy applied is similar as was done in TriangleGenerator.
   * We know pentagonal numbers are in the form "Pn=n(3n−1)/2".
   * Solving this to n gives us a way to fill in any number as Pn, and checking if the resulting n is a positive integer or not.
   * If it is, then we know Pn is a pentagonal number.
   *
   * P_n = n * (3*n−1) / 2
   * 2*P_n = 3*n^2 - n
   * 3*n^2 - n - 2*P_n = 0
   *
   * root 1: (1 + sqrt(1 + 24*P_n)) / 6
   * root 2: (1 - sqrt(1 + 24*P_n)) / 6
   *
   * As the P_n values we will test are always strictly positive the sqrt will always be greater than 1.
   * Because of this the second root will always be a strictly negative number, meaning we can safely ignore it, as it is always an invalid result.
   *
   * Simply testing whether a P_n produces an integer number using the first root's formula tells us if this number is pentagonal or not.
   *
   * @param num The number under test
   * @return True if the number is a pentagonal number
   */
  private boolean isPentagonal(long num) {
    double n = (1 + Math.sqrt(1 + 24*num)) / 6;
    return n == (long) n;
  }
}
