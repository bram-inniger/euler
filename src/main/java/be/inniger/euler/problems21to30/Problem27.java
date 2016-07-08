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
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * Euler discovered the remarkable quadratic formula:
 * n² + n + 41
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
 * The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.
 * Considering quadratics of the form:
 * n² + an + b, where |a| < 1000 and |b| < 1000
 * where |n| is the modulus/absolute value of n
 * e.g. |11| = 11 and |−4| = 4
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem27 implements Problem {

  private static final int LIMIT = 1000;

  /**
   * Straightforward solution, simply try out every combination of |a| < 1000 and |b| < 1000, then iterate n from 0 to INF until "n² + an + b" stops producing primes. 
   */
  @NotNull
  @Override
  public String solve() {
    int maxNrPrimes = -1;
    int maxA = 0;
    int maxB = 0;

    for (int a = -LIMIT+1 ; a < LIMIT; a++) {
      for (int b = -LIMIT+1; b < LIMIT; b++) {
        int n = 0;
        int nrPrimes = 0;

        while (Prime.isPrime(n*n + a*n +b)) {
          n++;
          nrPrimes++;
        }

        if (nrPrimes > maxNrPrimes) {
          maxNrPrimes = nrPrimes;
          maxA = a;
          maxB = b;
        }
      }
    }
    return "" + (maxA * maxB);
  }
}
