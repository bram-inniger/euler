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

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem09 implements Problem {

  private static final int SUM = 1000;

  @NotNull
  @Override
  public String solve() {
    int maxA = SUM / 3; // If a < b < c and a+b+c = 1000, then a+a+a < 1000, thus a < 1000/3, this is our upper bound for a
    int maxB = SUM / 2; // If a < b < c and a+b+c = 1000, then a+b+b < 1000, so b+b < 1000, thus b < 1000/2, this is our upper bound for b

    for (int a = 0; a < maxA; a++) {
      for (int b = a+1; b < maxB; b++) { // a < b, so the b counter can start from a + 1
        int c = SUM - a - b;
        if (a*a + b*b == c*c) {
          return "" + (a * b * c);
        }
      }
    }

    throw new RuntimeException("Error calculating Problem09, this should never happen! ");
  }
}
