package be.inniger.euler.problems01to10;

import be.inniger.euler.Problem;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/*
 * Multiples of 3 and 5
 *
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class Problem01 implements Problem {

  private static final int MAX_VALUE = 1000;

  @Override
  public long solve() {
    IntPredicate isMultipleOfThreeOrFive = isMultipleOf(3).or(isMultipleOf(5));

    return IntStream.range(0, MAX_VALUE)
        .filter(isMultipleOfThreeOrFive)
        .sum();
  }

  private IntPredicate isMultipleOf(int number) {
    return i -> i % number == 0;
  }
}
