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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem37 implements Problem {

  private static final int TOTAL_NR_PRIMES = 11;

  /**
   * The idea is again simple: keep generating next primes and checking them for truncability.
   * We will start from 10 and up, as single digit primes are not truncable.
   * Once the 11 truncable primes are found output their sum.
   */
  @NotNull
  @Override
  public String solve() {
    int sum = 0;
    int nrFound = 0;
    long prime = 10L; // Not an actual prime, but defines the starting value to look up the next prime from

    while (nrFound < TOTAL_NR_PRIMES) {
      prime = Prime.getNext(prime);

      if (isTruncable(prime)) {
        nrFound++;
        sum += prime;
      }
    }

    return "" + sum;
  }

  /**
   * Truncate a prime to the right and to the left.
   * To the right: to remove n digits from the right, divide the number by 10^n.
   * To the left: create a digit-mask of nrDigits-n length, and discard the other digits, in effect discarding the n left-most digits.
   *
   * @param prime The prime to check
   * @return True if the prime is truncable to the right and the left
   */
  private boolean isTruncable(long prime) {
    int nrDigits = Maths.getNrDigits(prime);

    for (int i = 1; i < nrDigits; i++) {
      long truncateRight = prime / Maths.pow(10, i);
      long truncateLeft = prime % Maths.pow(10, nrDigits-i);

      if (!Prime.isPrime(truncateRight) || !Prime.isPrime(truncateLeft)) {
        return false;
      }
    }

    return true;
  }
}
