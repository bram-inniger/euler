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

package be.inniger.euler.problems21to30;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
 * 012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem24 implements Problem {

  @SuppressWarnings("PointlessArithmeticExpression")
  private static final int NTH_PERMUTATION = 1 * 1000 * 1000;
  private static final int NR_DIGITS = 10;

  /**
   * Iterating over all possible iterations will take a long time (0123456789 -> 9876543210), as there are 10! = 3,628,800 possibilities.
   * A smarter strategy is the following:
   *   We know we are looking at the lexicographic ordering
   *   We know there are 10 digits
   *   We know there are 10! possibilities total
   *
   *   There will be 10 possible starting digits, followed by 9! possible other combinations (10 * 9! = 10!)
   *   -> The first group of 9! numbers will start with '0'
   *   -> The next group of 9! numbers will start with '1'
   *   -> ...
   *
   *   9! = 362,880
   *   -> The 1,000,000th permutation starts with 1,000,000 / 362,880 = 2
   *   -> The available digits in order are "0, 1, 2, 3, 4, 5, 6, 7, 8, 9"
   *   -> 2 is the most significant digit of our answer (in the list above it has index = 2, counting from 0)
   *
   *   The next number has then be one of "0, 1, 3, 4, 5, 6, 7, 8, 9"
   *   There are now 9! permutations left, and we care for number 1,000,000 - 2 * 9! = 274,240
   *   Apply the same strategy to get the next number (now in groups of 8! permutations)
   *   -> 274,240 / 8! = 6
   *   -> The second digit of our answer is the number on index 6 in the available digits above
   *   -> It is 7
   *
   *   ...
   */
  @NotNull
  @Override
  public String solve() {
    List<Integer> indices = calculateSolutionIndices();
    return getSolutionFromIndices(indices);
  }

  private List<Integer> calculateSolutionIndices() {
    List<Integer> indices = new ArrayList<>();
    int nthPermutation = NTH_PERMUTATION;

    for (int i = NR_DIGITS-1; i >= 0; i--) { // -1, as for 10 numbers, we group them in series of 9! = (10-1)!
      int factorial = Maths.smallFac(i);
      int index = (nthPermutation-1) / factorial; // -1, as we start counting from 0, not from 1

      indices.add(index);
      nthPermutation -= (index * factorial);
    }

    return indices;
  }

  private String getSolutionFromIndices(List<Integer> indices) {
    List<Integer> digits = IntStream.range(0, NR_DIGITS)
        .boxed()
        .collect(Collectors.toList());

    StringBuilder sb = new StringBuilder();
    for (int i : indices) {
      sb.append(digits.get(i));
      digits.remove(i);
    }

    return sb.toString();
  }
}
