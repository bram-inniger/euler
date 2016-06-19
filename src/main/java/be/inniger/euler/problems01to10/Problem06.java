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

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem06 implements Problem {

  private static final int FROM = 1;
  private static final int UNTIL = 100;

  @NotNull
  @Override
  public String solve() {
    List<Integer> naturals = IntStream
        .rangeClosed(FROM, UNTIL)
        .boxed()
        .collect(Collectors.toList());

    long sumOfSquares = getSumOfSquares(naturals);
    long squareOfSums = getSquareOfSums(naturals);
    return "" + (squareOfSums-sumOfSquares);
  }

  private long getSumOfSquares(List<Integer> list) {
    return list
        .stream()
        .mapToLong(i -> i*i)
        .sum();
  }

  private long getSquareOfSums(List<Integer> list) {
    long sum = list
        .stream()
        .mapToLong(Integer::longValue)
        .sum();
    return sum * sum;
  }
}
