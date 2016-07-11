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
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * How many circular primes are there below one million?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem35 implements Problem {

  @SuppressWarnings("PointlessArithmeticExpression")
  private static final int LIMIT = 1 * 1000 * 1000;

  /**
   * Count the number of circular primes below one million.
   * Start by generating all prime numbers up to one million.
   * For each check if they are circular primes or not.
   */
  @NotNull
  @Override
  public String solve() {
    int primeCount = 0;
    long prime = -1; // Not an actual prime value, but serves as a starting point

    while ((prime = Prime.getNext(prime)) < LIMIT) { // Get the next prime
      if (isCircularPrime(prime)) {                  // Check if it is a circular prime
        primeCount++;
      }
    }
    return "" + primeCount;
  }

  /**
   * Find out if a prime is a circular prime.
   * To do so we first find out the amount of digits the number has.
   * The amount of needed rotations equals #digits - 1
   *
   * We know a priori that the number, when it comes in, is a prime, so no need to test that.
   * Keep on rotating the digits from this point on until we return to the original number.
   * If all of the rotations tested as being prime themselves, return true.
   * How rotating works:
   * 1. Calculate the most significant digit and store it
   * 2. Perform the modulo of itself with a specific power of 10, in such a way that the most significant digit is removed
   * 3. Multiply the new number by 10, and sum it with the digit, effectively re-adding the removed digit as the now least-significant digit.
   * 4. Repeat #digits-1 times
   *
   * @param prime The prime number under test (assumes this number is indeed a prime)
   * @return True if the prime number is a circular prime
   */
  private boolean isCircularPrime(long prime) {
    int nrRotations = Maths.getNrDigits(prime) - 1;  // #Digits - 1
    int powerTen = (int) Maths.pow(10, nrRotations); // Will be smaller than 1 million anyway, so fits in an int

    for (int i = 0; i < nrRotations; i++) {
      long firstDigit = prime / powerTen;             // Store the most significant digit
      prime = prime % powerTen;                       // Remove the most significant digit from prime
      prime = prime*10 + firstDigit;                  // Re-attach it to prime, now as its leas significant digit

      if (!Prime.isPrime(prime)) {                    // Make sure every rotation is a prime number
        return false;
      }
    }

    return true;
  }
}
