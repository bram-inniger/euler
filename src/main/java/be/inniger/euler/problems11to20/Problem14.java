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

package be.inniger.euler.problems11to20;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;
import be.inniger.euler.util.CataclysmicException;

/**
 * Problem from Project Euler:
 * <p>
 * The following iterative sequence is defined for the set of positive integers:
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * Which starting number, under one million, produces the longest chain?
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem14 implements Problem {

  @SuppressWarnings("PointlessArithmeticExpression")
  private static final long MAX_START = 1 * 1000 * 1000;

  private final Map<Long, Long> chainLengths;


  /**
   * Simple constructor to set up the Map used throughout this class. 
   */
  public Problem14() {
    this.chainLengths = new HashMap<>();
    this.chainLengths.put(1L, 1L); // Starting value, the chain starting at value 1 is 1 long
  }

  @NotNull
  @Override
  public String solve() {
    calculateChainLengths();
    return "" + getMaxChainLength();
  }

  /**
   * Get all of the chain lengths.
   * The general strategy: follow the algorithm, and record the length.
   * Start at 1, go to MAX_START, and for every step in the chain check if it is already created, then the result is immediately known, and the old result can be reused.
   * This should save a whole lot of calculation.
   */
  private void calculateChainLengths() {
    for (long start = 1L; start < MAX_START; start++) {
      long num = start;
      long chainLength = 0L;

      while (true) {
        if (chainLengths.containsKey(num)) {
          chainLength += chainLengths.get(num);
          break;
        }

        if (num%2L == 0L) {
          num /= 2L;
        }
        else {
          num = 3L*num + 1L;
        }

        chainLength++;
      }

      chainLengths.put(start, chainLength);
    }
  }

  /**
   * Get the key associated to the largest value of chain lengths.
   * Stream all entries, sort by value, get a single one, reduce the entry to itself, then extract the key out of the Optional.
   *
   * @return The starting number resulting in the highest chain, using a Map of all chain-lengths and their starting values
   */
  private long getMaxChainLength() {
    return chainLengths.entrySet()
        .stream()
        .max((a, b) -> a.getValue().intValue() - b.getValue().intValue())
        .orElseThrow(CataclysmicException::new)
        .getKey();
  }
}
