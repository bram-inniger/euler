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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * It is possible to make £2 in the following way:
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * How many different ways can £2 be made using any number of coins?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem31 implements Problem {

  private static final int START_AMOUNT = 2 * 100;
  private static final List<Integer> ALL_COINS = Arrays.asList(200, 100, 50, 20, 10, 5, 2, 1);

  @NotNull
  @Override
  public String solve() {
    return "" + nrDifferentWays(START_AMOUNT, ALL_COINS);
  }

  private int nrDifferentWays(int amount, @NotNull List<Integer> coinsOrig) {
    if (coinsOrig.size() == 1) { // If there is only the last coin left (1p) and for example the amount equals 42, then there is exactly 1 way of arranging this (42 * 1p)
      return 1;
    }

    List<Integer> coins = new ArrayList<>(coinsOrig);
    int coin = coins.get(0);
    int timesLargestFits = amount / coin;
    coins.remove(0);

    return IntStream
        .rangeClosed(0, timesLargestFits)
        .parallel()
        .map(i -> nrDifferentWays(amount - i*coin, coins))
        .sum();
  }
}
