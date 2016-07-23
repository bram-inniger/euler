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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.DigitPermutationGenerator;
import be.inniger.euler.util.Maths;
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
 * What is the largest n-digit pandigital prime that exists?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem41 implements Problem {

  /**
   * Simple (but probably not most efficient) solution:
   * Generate all permutations of numbers 1 to n and test them for primeness.
   * Start with n = 9, and see if a solution is present, if so there is no need to check n = 8, as all numbers will be smaller anyway.
   * Keep searching the maximum prime for an ever decreasing "n" until the solution is discovered.
   */
  @NotNull
  @Override
  public String solve() {
    int nrDigits = 9;
    long largestPrime = -1;

    for (int i = nrDigits; i > 0; i--) {
      DigitPermutationGenerator gen = new DigitPermutationGenerator(i);
      while (gen.hasNext()) {
        long prime = gen.next();
        if (Prime.isPrime(prime) && prime > largestPrime) {
          largestPrime = prime;
        }
      }

      if (largestPrime > 0L) { // A solution is found!
        return "" + largestPrime;
      }
    }

    throw new RuntimeException("Cataclysmic event occurred, should not reach this... ");
  }
}
