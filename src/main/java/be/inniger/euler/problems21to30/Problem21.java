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

import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Maths;

/**
 * Problem from Project Euler:
 * <p>
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem21 implements Problem {

  private static final int LIMIT = 10000;

  @NotNull
  @Override
  public String solve() {
    return "" +
        IntStream.range(1, LIMIT)
            .filter(Maths::isAmicable)
            .sum();
  }
}
