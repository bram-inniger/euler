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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.CataclysmicException;
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * The first two consecutive numbers to have two distinct prime factors are:
 * 14 = 2 × 7
 * 15 = 3 × 5
 * The first three consecutive numbers to have three distinct prime factors are:
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 * Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem47 implements Problem {

  private static final int MAX_PRIME = 150_000;    // Chosen by trial and error
  private static final int NR_CONSECUTIVE = 4;
  private static final int NR_DISTINCT_PRIMES = 4;

  private final List<Integer> primesCache;
  private final Map<Integer, Set<Integer>> factorsCache;

  /**
   * Set up the problem by initialising the caches and pre-computing the primes.
   */
  public Problem47() {
    primesCache = preCalculatePrimes();
    factorsCache = new HashMap<>();
  }

  /**
   * The general idea of the solution is rather simple:
   * Iterate over all numbers starting from 1, and find out of there are more than NR_DISTINCT_PRIMES (4) in this number.
   * Keep count on success, reset the counter on failure. If the counter ever reaches NR_CONSECUTIVE (4), then a solution is found.
   * Finally keep a cache of handled values, to speed up this#getDistinctFactors(int). 
   */
  @NotNull
  @Override
  public String solve() {
    int count = 0;
    int num = 1;

    while (true) {
      Set<Integer> factors = getDistinctFactors(num);  // Get all factors
      if (factors.size() >= NR_DISTINCT_PRIMES) {      // Verify if there are enough factors
        count++;                                       // Keep track of the nr of consecutive numbers that had enough factors
        if (count == NR_CONSECUTIVE) {                 // A solution is found!
          return "" + (num + 1 - NR_CONSECUTIVE);      // Say the 4th consecutive number found was 42, then the return value should be 39 (= 42 + 1 - 4)
        }
      }
      else {                                           // Reset the counter back to 0 if there are not enough factors
        count = 0;
      }
      factorsCache.put(num, factors);                  // Cache the result, is used when computing the next number's factors
      num++;                                           // Increment the number and prepare for the next iteration
    }
  }

  /**
   * Pre-compute the prime values up until the trial-and-error chosen MAX_PRIME value.
   * These cached values are used again and again and again, giving a huge speed-boost.
   *
   * @return A List of prime numbers up to MAX_PRIME
   */
  @NotNull
  private List<Integer> preCalculatePrimes() {
    List<Integer> primes = new ArrayList<>();
    int prime = -1;   // Not an actual prime, just the starting value

    while ((prime = (int) Prime.getNext(prime)) < MAX_PRIME) {
      primes.add(prime);
    }

    return primes;
  }

  /**
   * Get the distinct prime factors of a (strictly positive) number.
   * Basically keep trying to remove prime factors starting in increasing order, and recording which factors were found until the number is done.
   * To speed things up a bit a cache of previously handled numbers is kept, and the computation will be halted as soon as possible if a previous answer can be re-used.
   *
   * N.B.: Will throw a CataclysmicException in the case that not enough primes were precomputed.
   * If this happens the program MAY otherwise produce an incorrect result, so instead a RuntimeException is thrown.
   *
   * @param num The number to get the distinct factors of
   * @return A Set containing the distinct factors of the number
   */
  @NotNull
  private Set<Integer> getDistinctFactors(int num) {
    Set<Integer> factors = new HashSet<>();

    for (int prime : primesCache) {
      while (num % prime == 0) {                // Fully remove a factor from num (e.g. if num = 12 it will remove the factor 2 twice)
        num /= prime;
        factors.add(prime);
      }

      if (num == 1) {                           // Done factoring, all factors have been accounted for, simply return
        return factors;
      }

      if (factorsCache.containsKey(num)) {       // What remains of num has already been handled: add the currently recorded factors together with the cached ones and return
        factors.addAll(factorsCache.get(num));
        return factors;
      }
    }

    throw new CataclysmicException();           // If this is reached it means not enough primes were pre-computed and the program may be false!
  }
}
