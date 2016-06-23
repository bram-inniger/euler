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

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Verification of the prime number detection and generation.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class PrimeTest {

  private static final String PRIMES_100_STRING =
      "2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239 241 251 257 263 269 271 277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509 521 523 541";
  private static final int MAX_PRIME = 541;

  private final List<Integer> primes100 = parseString(PRIMES_100_STRING);


  /**
   * Every number from 0 to MAX_PRIME will be tested on prime-ness using Prime#isPrime(long).
   * The results will be compared to the known primes above.
   */
  @Test
  public void isPrimeTest() {
    for (int i = 0; i <= MAX_PRIME; i++) {
      if (Prime.isPrime(i) != primes100.contains(i)) { // Mismatch
        fail("There was a mismatch comparing the number " + i);
      }
    }
  }

  /**
   * Verify that calling Prime#nextPrime(long) again and again results in the precomputed prime list above.
   */
  @Test
  public void nextPrimeTest() {
    long prime = 0L;

    for (int knownPrime : primes100) {
      prime = Prime.getNext(prime);

      if (prime != knownPrime) { // Mismatch
        fail("There was a mismatch comparing the number " + knownPrime);
      }
    }
  }

  /**
   * Parse a String into a list of Integers.
   *
   * @param nums Space-separated list of Integers
   * @return List of Integer Objects
   */
  private List<Integer> parseString(String nums) {
    return Arrays.asList(nums.split(" "))
        .stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }
}
