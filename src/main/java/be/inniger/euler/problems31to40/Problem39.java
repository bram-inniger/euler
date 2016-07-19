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

/**
 * Problem from Project Euler:
 * <p>
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
 * {20,48,52}, {24,45,51}, {30,40,50}
 * For which value of p ≤ 1000, is the number of solutions maximised?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem39 implements Problem {

  private static final int MAX_PERIMETER = 1000;

  /**
   * We will observe / enforce the following facts / rules:
   *
   * 0. a, b and c are larger than 0, otherwise it wouldn't be a triangle.
   * 1. p = a + b + c (this is how circles work)
   * 2. a and b will be the sides on the right angle, c will be the slanted (always longest) side -> a*a + b*b = c*c
   * 3. a <= b < c (for any a and b > 0, c has has to be larger than both a and b).
   *
   *
   * Following the rules above we can set up some nice bounds for our problem space.
   *
   * For a:
   * If   "a +  b + c = p"                  (rule 1)
   * and  "a <= b < c    "                  (rule 3)
   * then "a +  a + a < p" OR "a < p / 3"
   *
   * Combined with rule 0 we can say that a is bounded from 1 up to p/3 (exclusive)
   *
   *
   * For b:
   * If                   "a + b + c = p"         (rule 1)
   * then                 "    b + c < p"
   * We already know that "    b < c    " (rule 3)
   * Thus                 "    b + b < p" OR "b < p / 2"
   *
   * Combined with rule 3 again, we can say that b is bounded from a up p/2 (exclusive)
   *
   *
   * All that remains is try all of the combinations of a and b, for all possible p values.
   * Now verify that "a*a + b*b = c*c" (and "c > b") -> solution found.
   * Note that, as "p/3" and "p/2" can produce non integer numbers, we still need to bound a and b up to the rounded down values inclusive. 
   */
  @NotNull
  @Override
  public String solve() {
    int maxNrSolutions = -1;
    int maxSolutionsPerimeter = -1;

    for (int p = 0; p <= MAX_PERIMETER; p++) {
      int nrSolutions = 0;
      int boundA = p / 3;

      for (int a = 1; a <= boundA; a++) {
        int boundB = p / 2;

        for (int b = a; b <= boundB; b++) {
          int c = p - a - b;

          if ((c > b) && (a*a + b*b == c*c)) {
            nrSolutions++;
          }
        }
      }

      if (nrSolutions > maxNrSolutions) {
        maxNrSolutions = nrSolutions;
        maxSolutionsPerimeter = p;
      }
    }

    return "" + maxSolutionsPerimeter;
  }
}
