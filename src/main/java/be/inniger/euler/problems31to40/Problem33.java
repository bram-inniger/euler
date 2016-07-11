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

/**
 * Problem from Project Euler:
 * <p>
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 * <p>
 * <p>
 * NOTE: I will assume "trivial example" means a factor 10 was in play. The wording is rather vague...
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem33 implements Problem {

  /**
   * This problem is easily solved with a brute force approach, as there are less than 100*100 potential combinations to test.
   * However, a slightly more founded approach is more fun!
   *
   * Say we have the fraction 49/98, then we would call the cancelled digit 9 "x", the 4 that remains in the numerator "n" and the 8 from the denominator "d".
   * The task also states "n < d".
   * Furthermore the comment on trivial examples + that num and denom need to contain 2 numbers, we can say that x, d and n need to be in the range [1, 9].
   *
   * Bringing these together we already know:
   *    1 <= n < d <= 9
   *
   * There are 4 different ways of composing these fractions:
   * 1. (10*n + x) / (10*d + x)
   * 2. (10*x + n) / (10*x + d)
   * 3. (10*x + n) / (10*d + x)
   * 4. (10*n + x) / (10*x + d)
   *
   * Solving the first composition:
   *    (10*n + x) / (10*d + x) = n / d
   *    d * (10*n + x) = n * (10*d + x)
   *    10*n*d + x*d = 10*n*d + x*n
   *    x*d = x*n
   *    d = n
   * This is impossible, as we know "d > n"
   * ==> No solutions of this form
   *
   * Solving the second composition:
   *    (10*x + n) / (10*x + d) = n / d
   *    d * (10*x + n) = n * (10*x + d)
   *    10*x*d + d*n = 10*x*n + d*n
   *    10*x*d = 10*x*n
   *    d = n
   * Again, this is impossible, as we know "d > n"
   * ==> Also no solutions of this form
   *
   * This means our solution will be in the form of the third or fourth composition!
   *
   * P.S.: The Internet showed me we can go even deeper here, and rule out some more positions.
   * However, I am out to keep honing my programming, and refreshing my maths, not copy someone else's thinking / solution.
   * This is as far as I got myself optimising, and it more than fulfills its intended purpose!
   */
  @NotNull
  @Override
  public String solve() {
    int numeratorProd = 1;
    int denominatorProd = 1;

    for (int n = 1; n <= 9; n++) {                    // Let n go from 1 up to 9 included
      for (int d = n+1; d <= 9; d++) {                // Let d go from n+1 up to 9 included (as "d > n")
        for (int x = 1; x <= 9; x++) {                // Try every value of "x" (the to be negated digit)
          if (d * (10*x + n) == n * (10*d + x) ||     // Matches the third potential composition above
              d * (10*n + x) == n * (10*x + d)) {     // Matches the fourth potential composition above
            numeratorProd *= n;
            denominatorProd *= d;
          }
        }
      }
    }

    // To minimise a fraction "a/b" into its lowest terms, simply divide both by GCD(a,b), as it will, by definition, remove all the shared factors
    int minimalDenominator = denominatorProd / (int) Maths.gcd(numeratorProd, denominatorProd);
    return "" + minimalDenominator;
  }
}
