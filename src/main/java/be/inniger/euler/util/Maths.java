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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * General use mathematics related utilities.
 * Names "Maths" to evade namespace issues with "java.lang.Math".
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Maths {

  /**
   * Power calculation of a base to its exponent.
   *
   * @param base The base
   * @param exp The exponent
   * @return The power of the base to its exponent
   */
  public static long pow(long base, long exp) {
    long result = 1;
    for (int i = 0; i < exp; i++) {
      result *= base;
    }

    return result;
  }

  /**
   * Factorise a number.
   *
   * @param num Number to factorise
   * @return The factors in a Map, the key being the factor, and the value how often it occurs
   */
  @NotNull
  public static Map<Long, Integer> getFactors(long num) {
    Map<Long, Integer> factors = new HashMap<>();
    if (num <= 1L) {
      return factors;
    }

    long prime = Prime.getNext(0L);
    while (num > 1L) {
      while (num % prime == 0L) {
        num /= prime;

        if (factors.containsKey(prime)) {
          factors.put(prime, factors.get(prime) + 1);
        }
        else {
          factors.put(prime, 1);
        }
      }

      prime = Prime.getNext(prime);
    }
    return factors;
  }

  /**
   * Get all of the divisors of a number.
   *
   * @param num The number to get the divisors of
   * @return The divisors in the form of an ordered list
   */
  @NotNull
  public static List<Long> getDivisorsOrdered(long num) {
    Set<Long> divisorsSet = getDivisors(num);
    List<Long> divisors = new ArrayList<>(divisorsSet);
    divisors.sort(Comparator.naturalOrder());
    return divisors;
  }

  /**
   * Get all of the divisors of a number.
   *
   * @param num The number to get the divisors of
   * @return The divisors in the form of an unordered set (no duplicates)
   */
  @NotNull
  public static Set<Long> getDivisors(long num) {
    Set<Long> divisors = new HashSet<>();
    long upperBound = (long) Math.sqrt(num); // The largest divisor, apart from num itself, cannot be larger than sqrt(num)

    for (long i = 1; i <= upperBound; i++) {
      if (num%i == 0) {
        divisors.add(i); // Say num = 42 and i = 2 ==> 42 / 2 = 21, thus i is a divisor
        divisors.add(num/i); // But this also means ==> 42 / 21 = 2, thus num/i is also a divisor
      }
    }
    
    return divisors;
  }
}
