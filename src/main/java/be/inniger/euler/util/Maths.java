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

import java.util.HashMap;
import java.util.Map;
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

  @NotNull
  public static Map<Long, Integer> getFactors(int num) {
    Map<Long, Integer> factors = new HashMap<>();
    if (num <= 1) {
      return factors;
    }

    long prime = Prime.getNext(0);
    while (num > 1) {
      while (num % prime == 0) {
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
}
