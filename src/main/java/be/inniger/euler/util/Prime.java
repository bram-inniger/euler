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

package be.inniger.euler.util;

/**
 * Util methods to work with prime numbers.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Prime {

  /**
   * Get the next prime number.
   *
   * @param from Starting point to look (non-inclusive)
   * @return The next prime number after the from point
   */
  public static long getNext(long from) {
    if (from <= 3) {
      return getNextNaive(from);
    }
    else {
      return getNextSmart(from);
    }
  }

  /**
   * Naive way of finding out if it is a prime, works fine for small numbers.
   *
   * @param from Some small prime (non-inclusive)
   * @return The next prime number after the from point
   */
  private static long getNextNaive(long from) {
    while (true) {
      from++;

      if (isPrime(from)) {
        return from;
      }
    }
  }

  /**
   * Smarter way of getting the next prime, increments in batches of 6, just like the isPrime() method.
   * Only trick here is setting the from variable to the nearest value that can be incremented like this.
   *
   * @param from Starting point to look (non-inclusive)
   * @return The next prime number after the from point
   */
  private static long getNextSmart(long from) {
    // First bring "from" to the nearest *higher* number in the "5 + 6x" and "7 + 6x" format
    boolean done = false;
    while (!done) {
      from++;

      if (from%2 != 0 && from%3 != 0) {
        done = true;
      }
    }

    // Check that it is not the upper of the 2 (check that is conforms to 5 + 6x and not 7 + 6x)
    if ((from+2) % 3 == 0) {
      if (isPrime(from)) {
        return from;
      }

      from += 4; // Bring it from the 7 + 6x form to the 5 + 6x form
    }

    // Start incrementing in batches of 6
    while (true) {
      if (isPrime(from)) {
        return from;
      }
      if (isPrime(from + 2)) {
        return from + 2;
      }

      from += 6;
    }
  }

  /**
   * Determine if a given number is a prime.
   *
   * @param value The number under test
   * @return True if the number is a prime number
   */
  public static boolean isPrime(long value) {
    if (value <= 1L) { // Catch all safety measure, in case negative numbers or obvious non-primes are offered
      return false;
    }
    else if (value < 4L) {
      return true;
    }
    else if (value % 2L == 0L) {
      return false;
    }
    else if (value % 3L == 0L) {
      return false;
    }
    else if (value < 25L) { // All non-primes below 25 have been filtered at this point
      return true;
    }
    else {
      long upperLim = (long) Math.floor(Math.sqrt(value));
      long l = 5L;

      while (l <= upperLim) {
        if (value % (l) == 0L || value % (l + 2L) == 0L) {
          return false;
        }
        l += 6L;
      }
      return true;
    }
  }
}
