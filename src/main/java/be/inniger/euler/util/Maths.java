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

package be.inniger.euler.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * General use mathematics related utilities.
 * Names "Maths" to evade namespace issues with "java.lang.Math".
 *
 * @author Bram Inniger
 * @version 1.0
 */
public class Maths {

  /**
   * Power calculation of a base to its exponent.
   *
   * @param base The base
   * @param exp  The exponent
   * @return The power of the base to its exponent
   */
  public static long pow(long base, long exp) {
    long result = 1;
    for (int i = 0; i < exp; i++) {
      result *= base;
    }

    return result;
  }

  /**
   * Factorise a number.
   *
   * @param num Number to factorise
   * @return The factors in a Map, the key being the factor, and the value how often it occurs
   */
  @NotNull
  public static Map<Long, Integer> getFactors(long num) {
    Map<Long, Integer> factors = new HashMap<>();
    if (num <= 1L) {
      return factors;
    }

    long prime = Prime.getNext(0L);
    while (num > 1L) {
      while (num % prime == 0L) {
        num /= prime;

        if (factors.containsKey(prime)) {
          factors.put(prime, factors.get(prime) + 1);
        }
        else {
          factors.put(prime, 1);
        }
      }

      prime = Prime.getNext(prime);
    }
    return factors;
  }

  /**
   * Get all of the divisors of a number.
   *
   * @param num The number to get the divisors of
   * @return The divisors in the form of an ordered list
   */
  @NotNull
  public static List<Long> getDivisorsOrdered(long num) {
    Set<Long> divisorsSet = getDivisors(num);
    List<Long> divisors = new ArrayList<>(divisorsSet);
    divisors.sort(Comparator.naturalOrder());
    return divisors;
  }

  /**
   * Get all of the divisors of a number.
   *
   * @param num The number to get the divisors of
   * @return The divisors in the form of an unordered set (no duplicates)
   */
  @NotNull
  public static Set<Long> getDivisors(long num) {
    Set<Long> divisors = new HashSet<>();
    long upperBound = (long) Math.sqrt(num); // The largest divisor, apart from num itself, cannot be larger than sqrt(num)

    for (long i = 1; i <= upperBound; i++) {
      if (num % i == 0) {
        divisors.add(i); // Say num = 42 and i = 2 ==> 42 / 2 = 21, thus i is a divisor
        divisors.add(num / i); // But this also means ==> 42 / 21 = 2, thus num/i is also a divisor
      }
    }

    return divisors;
  }

  /**
   * Calculate the factorial of a number.
   *
   * @param num Number to take the factorial of
   * @return The factorial of a number
   */
  @NotNull
  public static BigInteger fac(int num) {
    BigInteger fact = BigInteger.ONE;

    for (int i = num; i > 0; i--) {
      fact = fact.multiply(BigInteger.valueOf(i));
    }

    return fact;
  }

  /**
   * Calculate the factorial of a small number.
   *
   * @param num Number to take the factorial of
   * @return The factorial of a number
   */
  public static int smallFac(int num) {
    int fact = 1;

    for (int i = num; i > 0; i--) {
      fact *= i;
    }

    return fact;
  }

  /**
   * Find out whether a number is part of an amicable pair.
   *
   * @param num The number to be examined
   * @return True if the number is part of an amicable pair
   */
  public static boolean isAmicable(int num) {
    int sum = sumProperDivisors(num);
    return sum != num && sumProperDivisors(sum) == num;
  }

  /**
   * Get the sum of all the proper divisors.
   * Simply summing all of the divisors, and subtracting the original number will provide this.
   *
   * @param num Number to get the sum of divisors of
   * @return The sum of the proper divisors
   */
  public static int sumProperDivisors(int num) {
    return getDivisors(num).stream()
        .mapToInt(Long::intValue)
        .sum()
               - num;
  }

  /**
   * Decompose a number into a list of its digits (in the logical order).
   *
   * @param num The number to get the digits of
   * @return A list of the number's digits
   */
  public static List<Integer> getDigits(long num) {
    if (num == 0) {
      return Collections.singletonList(0);
    }

    num = Math.abs(num);
    List<Integer> digits = new LinkedList<>();

    while (num != 0) {
      digits.add(0, (int) (num%10));
      num /= 10;
    }

    return digits;
  }

  /**
   * Get the number of digits in base 10 of an integer number
   *
   * @param num Number to calculate the number of digits of
   * @return The number of digits in the base 10 representation of the number
   */
  public static int getNrDigits(long num) {
    if (num == 0) {
      return 1;
    }

    return (int) (Math.floor(Math.log10(Math.abs(num)))) + 1;
  }

  /**
   * Check whether a number is pandigital from 1 to 9 inclusive.
   *
   * @param num The number to check
   * @return True if this is indeed a pandigital number
   */
  public static boolean isPandigital(long num) {
    final int nrDigits = 9;  // No magic numbers
    return isPandigital(num, nrDigits);
  }

  /**
   * Check whether a number is pandigital (i.e. it contains all the digits exactly once from 1 to n).
   *
   * @param num The number to check
   * @param n The nr of digits to check against
   * @return True if this is indeed a pandigital number
   */
  public static boolean isPandigital(long num, int n) {
    List<Integer> digits = getDigits(num);
    return digits.size() == n &&  // If there are more different digits present than n (e.g. n=2 and num=123) then it cannot be pandigital
        IntStream
            .rangeClosed(1, n)
            .filter(digits::contains)
            .count()
                         == n;    // Check if all numbers from 1 to n are present in the digits-list
  }

  /**
   * Get a part out of a number (similar to String#substring(int, int)).
   * This operation assumes correct indexes, otherwise it will produce unexpected results.
   *
   * @param num The number a part will be taken from
   * @param from The starting index, inclusive, starting from 0
   * @param to The end index, exclusive, starting from 0
   * @return The sub-part of the number
   */
  public static long getPart(long num, int from, int to) {
    return getPart(num, from, to, getNrDigits(num));
  }

  /**
   * Get a part out of a number (similar to String#substring(int, int)).
   * This operation assumes correct indexes and sizes, otherwise it will produce unexpected results.
   *
   * @param num The number a part will be taken from
   * @param from The starting index, inclusive, starting from 0
   * @param to The end index, exclusive, starting from 0
   * @param size The size of the number (amount of digits in base 10)
   * @return The sub-part of the number
   */
  public static long getPart(long num, int from, int to, int size) {
    return (num                   // Start with num
        % pow(10, size - from))   // Remove the unneeded leading digits
        / pow(10, size - to);     // Remove the unneeded trailing digits
  }

  /**
   * Calculate the Greatest Common Divisor (GCD) between 2 numbers.
   * This method uses Euclid's Algorithm:
   *    gcd(a, 0) = a
   *    gcd(a, b) = gcd(b, a%b)
   * Assume a and b are positive.
   *
   * @param a The first number to compute the GCD from
   * @param b The second number to compute the GCD from
   * @return The GCD between a and b
   */
  public static long gcd(long a, long b) {
    if (b == 0) { // Termination check
      return a;
    }

    return gcd(b, a%b);
  }

  /**
   * Concatenate the digits of 2 numbers.
   * E.g. "concatenate(1234, 5678)" -> 12345678
   *
   * @param a The leading part of the concatenated number
   * @param b The trailing part of the concatenated number
   * @return The full concatenated number
   */
  public static long concatenate(long a, long b) {
    return a * pow(10, getNrDigits(b)) + b;
  }

  /**
   * Check if a number is a square number.
   * Simply calculate the square root, and verify that it is an integer number.
   *
   * @param num The number to test
   * @return True if the number is indeed a square number
   */
  public static boolean isSquare(long num) {
    double root = Math.sqrt(num);
    return root == (long) root;
  }
}
