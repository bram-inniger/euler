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

import java.math.BigInteger;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * The Fibonacci sequence is defined by the recurrence relation:
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * Hence the first 12 terms will be:
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * F5 = 5
 * F6 = 8
 * F7 = 13
 * F8 = 21
 * F9 = 34
 * F10 = 55
 * F11 = 89
 * F12 = 144
 * The 12th term, F12, is the first term to contain three digits.
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem25 implements Problem {

  private static final int NR_DIGITS = 1000;
  private static final double PHI = (1 + Math.sqrt(5)) / 2;

  /**
   * One could simply generate Fibonacci number after number, and stop when it contains more than 1000 digits.
   * This would however be a slow and wasteful solution.
   * <p>
   * It is known that the ratio between the next Fibonacci number and the current one converges to φ (phi or golden ratio) the larger the number becomes.
   * This nicely leads to the following interesting formula:
   * Fn = round( φ^n / sqrt(5) )
   * Where:
   * round = rounding to the nearest integer
   * Fn = nth Fibonacci number
   * φ = (1 + sqrt(5)) / 2
   * n = index in the Fibonacci sequence
   * <p>
   * To solve: simply plug the smallest 1000 digit number in this equation (10^999 = 10^(NR_DIGITS-1)) and solve for n.
   * Round up n to get the index of the nearest Fibonacci number larger than the smallest 1000 digit number -> the solution!
   * <p>
   * Solve the equation for "n" starts by taking log10 of both sides:
   * LHS:
   * log10(Fn) = log10(10^(NR_DIGITS-1)) = NR_DIGITS-1
   * <p>
   * RHS:
   * log10(φ^n / sqrt(5)) = log10(φ^n) - log10(sqrt(5)) = n * log10(φ) - 1/2 * log10(sqrt(5))
   * <p>
   * Bringing both sides together and moving n to the LHS and everything else to the RHS we get:
   * n = (NR_DIGITS - 1 - 1/2 * log10(sqrt(5))) / log10(φ)
   */
  @NotNull
  @Override
  public String solve() {
    double n = (NR_DIGITS - 1 - 1 / 2 * Math.log10(Math.sqrt(5))) / Math.log10(PHI);
    return "" + (int) Math.ceil(n + 1); // n+1 because the mathematical formula starts the sequence at index 0, and Euler starts it at index 1
//    return solveNaive();
  }

  /**
   * The obvious solution of just getting the next Fibonacci number until you get one larger than 1000 digits.
   */
  @SuppressWarnings("unused") // Serves as an example of the un-optimised straightforward solution
  private String solveNaive() {
    BigInteger num1 = BigInteger.ONE;
    BigInteger num2 = BigInteger.ONE;
    int index = 1;

    while (num1.toString().length() < NR_DIGITS) {
      BigInteger tmp = num1;
      num1 = num2;
      num2 = num2.add(tmp);
      index++;
    }

    return "" + index;
  }
}
