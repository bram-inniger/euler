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

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 * It turns out that the conjecture was false.
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem46 implements Problem {

  private final List<Long> primes;

  /**
   * Set up the list of primes for later usage.
   * As we are only concerning us with odd numbers below, we need to add the single even prime ourselves already.
   */
  public Problem46() {
    primes = new ArrayList<>();
    primes.add(2L);
  }

  /**
   * The easiest solution is just looping over all ood composite numbers in increasing order.
   * For every number test whether it complies to the conjecture, if it doesn't we found the required answer.
   *
   * Looping over the odd composites is done by checking all odd numbers starting from 3, then 5, 7, 9, ...
   * If the generated number is not a prime, then it has to be an odd composite.
   *
   * For speed reasons we store all primes discovered so far, as they are used in this#followsConjecture().
   */
  @NotNull
  @Override
  public String solve() {
    long num = 3;               // Starting odd number, 1 is skipped as it is a special case (neither a prime nor a composite)

    while (true) {
      if (Prime.isPrime(num)) { // num is a prime number
        primes.add(num);
      }
      else {                    // num is an odd composite number
        if (!followsConjecture(num)) {
          return "" + num;
        }
      }

      num += 2;
    }
  }

  /**
   * Test whether a number follows the conjecture or not.
   * We can write the conjecture as "num = prime + 2*n^2", where n is any positive integer number.
   * It is immediately clear that, as "2*n^2 > 0" for any "n", it follows that "num > prime".
   * Meaning that if we want to test any number, we should concern ourselves with all prime-values lower than that number.
   * Directly following from the way this#solve() works, the list of primes will already always contain ALL primes lower than the number.
   *
   * This reduces the problem to the following:
   * Test for every prime smaller than num whether the formula above produces an integer n.
   * If it does, then we know num indeed follows the conjecture, and we can return true.
   * However, if no single prime corresponds to a valid n we can prove the conjecture wrong and return false.
   *
   * We can solve the formula to n like this:
   * n = sqrt((num - prime) / 2)
   * Checking if "(num - prime) / 2" is indeed a square number is sufficient to prove/disprove the conjecture, as it will tell if n is an integer or not.
   * Do this for every prime, and return as soon as possible if a conclusive result is found (speed reasons)
   *
   * @param num The number under test
   * @return True if the number follows the conjecture
   */
  private boolean followsConjecture(long num) {
    return primes
        .stream()                           // Loop over all primes
        .map(prime -> (num - prime) / 2)    // Transform num into n^2
        .filter(Maths::isSquare)            // Test whether n (from n^2) is actually an integer
        .findFirst()                        // Once ANY integer n is found stop looping, we are done
        .isPresent();                       // Check if ANY was found, if so the conjecture holds, if not it is proven false
  }
}
