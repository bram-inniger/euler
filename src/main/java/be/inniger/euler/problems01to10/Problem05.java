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

package be.inniger.euler.problems01to10;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem05 implements Problem {

  @NotNull
  @Override
  public String solve() {
    long solution = getAllFactors(20)
        .entrySet()
        .stream()
        .mapToLong(i -> Maths.pow(i.getKey(), i.getValue()))
        .reduce(1, (acc, i) -> acc *= i);
    return "" + solution;
  }

  /**
   * Get all the factors that exist in all of the numbers ranging from 1 to num.
   * E.g.: 10 has factors 2 and 5, 4 has factors 2 and 2.
   * As one of 4's "2" factors already exist in 10, the total factors would be "two times 2, one time 5".
   *
   * @return The minimal amount of factors that keep it all divisible
   */
  @NotNull
  private Map<Long, Integer> getAllFactors(int num) {
    Map<Long, Integer> allFactors = new HashMap<>();

    for (int i = 1; i <= num; i++) {
      Map<Long, Integer> factors = Maths.getFactors(i);

      for (long key : factors.keySet()) {
        if (allFactors.containsKey(key)) {
          int newVal = factors.get(key);

          if (newVal > allFactors.get(key)) {
            allFactors.put(key, newVal);
          }
        }
        else {
          allFactors.put(key, factors.get(key));
        }
      }
    }

    return allFactors;
  }
}
