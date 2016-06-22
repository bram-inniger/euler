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

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import be.inniger.euler.Problem;

/**
 * Problem from Project Euler:
 * <p>
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
 * The use of "and" when writing out numbers is in compliance with British usage.
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Problem17 implements Problem {

  private static final List<String> DIGITS =
      Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
  private static final List<String> TENS =
      Arrays.asList("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety");
  private static final List<String> EXCEPTIONS = // These need to be treated separately, cause English...
      Arrays.asList("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen");

  private static final int THOUSAND = "one".length() + "thousand".length();
  private static final int HUNDRED = "hundred".length();
  private static final int AND = "and".length();


  /**
   * Calculate the total sum of all numbers up to 1000 in 3 steps:
   *   1 -> 99
   *   100 -> 900
   *   10000
   *
   * @return The sum of letters from 1 up to 1000
   */
  @NotNull
  @Override
  public String solve() {
    int sum =
              calculateFirstHundredSum()
            + calculateHundredsSum()
            + THOUSAND;
    return "" + sum;
  }

  /**
   * Calculate the sum of all the numbers below 100 (1 -> 99).
   * To do this sum the result of 3 different methods:
   *    1 -> 9
   *    10 -> 19
   *    20 -> 99
   *
   * @return Sum of all the numbers up until 99
   */
  private int calculateFirstHundredSum() {
    return calculateDigitsSum()
         + calculateExceptionsSum()
         + calculateTensSum();
  }

  /**
   * Calculate the sum of all of the digits (1 -> 9)
   *
   * @return The sum of all the numbers from 1 to 9
   */
  private int calculateDigitsSum() {
    return DIGITS.stream()
        .mapToInt(String::length)
        .sum();
  }

  /**
   * Purely for the exception numbers (10 -> 19)
   *
   * @return The sum of all the numbers from 10 up to 19
   */
  private int calculateExceptionsSum() {
    return EXCEPTIONS.stream()
        .mapToInt(String::length)
        .sum();
  }

  /**
   * Calculate the sum of all of the regular ten-range numbers (20 -> 99).
   * E.G. the thirties:
   *   thirty + thirty-one + thirty-two + ... + thirty-nine
   * = (thirty + thirty + ... + thirty) + (one + two + ... + nine)
   * = 10 * (thirty)                    + calculateDigitsSum()
   *
   * @return The sum of all the numbers from 20 up to 99
   */
  private int calculateTensSum() {
    int digitsSum = calculateDigitsSum();

    return TENS.stream()
        .mapToInt(ten -> 10 * ten.length() + digitsSum)
        .sum();
  }

  /**
   * Calculate the sum of the hundred-range numbers (100 -> 999).
   * E.g. the four hundreds:
   *   four hundred + four hundred and one + four hundred and two + four hundred and three + ... + four hundred and ninety-nine
   * = (four hundred + four hundred + ... + four hundred) + (and + and + ... + and) + (one + two + three + ... + ninety-nine)
   * = 100 * (four hundred)                               + 99 * (and)              + calculateFirstHundredSum()
   *
   * @return The sum of all the numbers from 100 up to 999
   */
  private int calculateHundredsSum() {
    int untilNinetyNine = calculateFirstHundredSum();
    return DIGITS.stream()
        .mapToInt(digit -> 100 * (digit.length() + HUNDRED) + 99 * AND + untilNinetyNine)
        .sum();
  }
}
