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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
 * <p>
 * Check Problem13.txt
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem13 implements Problem {

  @NotNull
  @Override
  public String solve() {
    BigInteger sum = getNumbers()
        .stream()
        .reduce(BigInteger.ZERO, BigInteger::add);
    return sum.toString().substring(0, 10);
  }

  /**
   * Get a List of all numbers as BigIntegers.
   *
   * @return A List of BigIntegers representing the numbers
   */
  private List<BigInteger> getNumbers() {
    List<BigInteger> ints = new ArrayList<>();
    File numbersFile = new File("src/main/resources/problems/Problem13.txt");

    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(numbersFile)));
      String line;

      while ((line = br.readLine()) != null) {
        ints.add(new BigInteger(line));
      }
    }
    catch (IOException e) {
      throw new RuntimeException("The unexpected happened, cannot recover: " + e.getMessage());
    }

    return ints;
  }
}
