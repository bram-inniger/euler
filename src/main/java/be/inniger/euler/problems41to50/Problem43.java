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

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.DigitPermutationGenerator;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 * d2d3d4=406 is divisible by 2
 * d3d4d5=063 is divisible by 3
 * d4d5d6=635 is divisible by 5
 * d5d6d7=357 is divisible by 7
 * d6d7d8=572 is divisible by 11
 * d7d8d9=728 is divisible by 13
 * d8d9d10=289 is divisible by 17
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem43 implements Problem {

  /**
   * TODO: rewrite this naive solution to a more optimised one
   *
   * Stream over all permutations containing the digits 0 to 9 (inclusive), these are by definition exactly all of the pandigital numbers.
   * Test them if they follow the property above of String divisibility.
   * Sum the ones that do: done!
   */
  @NotNull
  @Override
  public String solve() {
    return "" +
        streamFrom(new DigitPermutationGenerator(0, 9))
            .filter(this::isSubStringDivisable)
            .mapToLong(Long::longValue)
            .sum();
  }

  /**
   * Create a Stream from any arbitrary Iterator.
   *
   * @param iterator The Iterator to make a Stream of
   * @param <T> The type of the Iterator and the resulting Stream
   * @return A Stream from the Iterator
   */
  private <T> Stream<T> streamFrom(Iterator<T> iterator) {
    Iterable<T> iterable = () -> iterator;
    return StreamSupport.stream(iterable.spliterator(), true);
  }

  /**
   * Test whether a number has the String divisibility property described above.
   *
   * @param num The number under test
   * @return True if the number is String divisible
   */
  private boolean isSubStringDivisable(long num) {
    return Maths.getPart(num, 7, 10) % 17 == 0
        && Maths.getPart(num, 6, 9) % 13 == 0
        && Maths.getPart(num, 5, 8) % 11 == 0
        && Maths.getPart(num, 4, 7) % 7 == 0
        && Maths.getPart(num, 3, 6) % 5 == 0
        && Maths.getPart(num, 2, 5) % 3 == 0
        && Maths.getPart(num, 1, 4) % 2 == 0;
  }
}
