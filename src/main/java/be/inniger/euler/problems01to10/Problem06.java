package be.inniger.euler.problems01to10;

import be.inniger.euler.util.Math;

import java.util.stream.IntStream;

/**
 * Sum square difference
 * <p>
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class Problem06 {

  private static final int MAX_VALUE = 100;

  public int solve() {
    var squareOfSums = IntStream.rangeClosed(1, MAX_VALUE).boxed().reduce(Math::sum).map(Math::square).orElseThrow();
    var sumOfSquares = IntStream.rangeClosed(1, MAX_VALUE).boxed().map(Math::square).reduce(Math::sum).orElseThrow();

    return squareOfSums - sumOfSquares;
  }
}
