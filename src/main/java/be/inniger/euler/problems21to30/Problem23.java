package be.inniger.euler.problems21to30;

import be.inniger.euler.Problem;

import java.util.Set;
import java.util.stream.IntStream;

import static be.inniger.euler.util.Math.roundedSqrt;
import static java.util.stream.Collectors.toUnmodifiableSet;

/*
 * Non-abundant sums
 *
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class Problem23 implements Problem {

  private static final int LIMIT = 28123;

  @Override
  public long solve() {
    var abundantNumbers = IntStream.rangeClosed(1, LIMIT)
        .filter(number -> getProperDivisorsSum(number) > number)
        .boxed()
        .collect(toUnmodifiableSet());
    
    return IntStream.rangeClosed(1, LIMIT)
        .filter(number -> !isSumOfTwoAbundantNumbers(number, abundantNumbers))
        .sum();
  }

  // TODO extract this one and the one from Problem21 into Math
  private int getProperDivisorsSum(int number) {
    return IntStream.rangeClosed(1, roundedSqrt(number))
        .filter(i -> number % i == 0)
        .flatMap(i -> i == 1 || i == number / i ? IntStream.of(i) : IntStream.of(i, number / i))
        .sum();
  }

  // TODO improve speed here
  private boolean isSumOfTwoAbundantNumbers(int number, Set<Integer> abundantNumbers) {
    return abundantNumbers.stream()
        .anyMatch(abundantNumber -> abundantNumbers.contains(number - abundantNumber));
  }
}
