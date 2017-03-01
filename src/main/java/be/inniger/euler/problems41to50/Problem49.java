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

import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.CataclysmicException;
import be.inniger.euler.util.DigitPermutationGenerator;
import be.inniger.euler.util.Prime;

/**
 * Problem from Project Euler:
 * <p>
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem49 implements Problem {

  private static final int INCREASE = 3330;
  private static final int LOWEST_FOUR_DIGIT = 1000;
  private static final int HIGHEST_FOUR_DIGIT = 9999;
  private static final int FIRST_SOLUTION = 1487;

  @NotNull
  @Override
  public String solve() {
    int solution = IntStream
        .rangeClosed(LOWEST_FOUR_DIGIT, HIGHEST_FOUR_DIGIT - 2*INCREASE)
        .filter(this::isSolution)
        .findFirst()
        .orElseThrow(CataclysmicException::new);

    return "" + solution + (solution + INCREASE) + (solution + 2*INCREASE);
  }

  private boolean isSolution(int num1) {
    int num2 = num1 + INCREASE;
    int num3 = num2 + INCREASE;

    return Prime.isPrime(num1)
        && Prime.isPrime(num2)
        && Prime.isPrime(num3)
        && DigitPermutationGenerator.isDigitPermutation(num1, num2)
        && DigitPermutationGenerator.isDigitPermutation(num1, num3)
        && num1 != FIRST_SOLUTION;
  }
}
