package be.inniger.euler.problems01to10;

import be.inniger.euler.Problem;

import java.util.stream.IntStream;

import static be.inniger.euler.util.Palindrome.isPalindrome;
import static java.util.stream.Collectors.toUnmodifiableList;

/*
 * Largest palindrome product
 *
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Problem04 implements Problem {

  private static final int BIGGEST_N_DIGIT_NUMBER = 999;
  private static final int SMALLEST_N_DIGIT_NUMBER = 100;

  @Override
  public long solve() {
    return IntStream.rangeClosed(SMALLEST_N_DIGIT_NUMBER, BIGGEST_N_DIGIT_NUMBER)
        .flatMap(first -> IntStream.rangeClosed(SMALLEST_N_DIGIT_NUMBER, first)
            .map(second -> first * second))
        .filter(number -> isPalindrome(
            String.valueOf(number)
                .chars()
                .boxed()
                .collect(toUnmodifiableList())))
        .max()
        .orElseThrow();
  }
}
