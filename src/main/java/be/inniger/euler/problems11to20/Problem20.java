package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Math;

/*
 * Factorial digit sum
 *
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * Find the sum of the digits in the number 100!
 */
public class Problem20 implements Problem {

  private static final int NUMBER = 100;

  @Override
  public long solve() {
    return Math.factorial(NUMBER)
        .toString()
        .chars()
        .map(Character::getNumericValue)
        .sum();
  }
}
