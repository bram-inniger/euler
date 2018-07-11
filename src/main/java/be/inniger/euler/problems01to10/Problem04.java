package be.inniger.euler.problems01to10;

import java.util.stream.IntStream;

import static be.inniger.euler.util.Palindrome.isPalindrome;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

/**
 * Largest palindrome product
 * <p>
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Problem04 {

  private static final int BIGGEST_N_DIGIT_NUMBER = 999;
  private static final int SMALLEST_N_DIGIT_NUMBER = 100;

  public int solve() {
    return IntStream.rangeClosed(SMALLEST_N_DIGIT_NUMBER, BIGGEST_N_DIGIT_NUMBER)
        .boxed()
        .flatMap(left ->
            IntStream.rangeClosed(SMALLEST_N_DIGIT_NUMBER, left)
                .mapToObj(right -> new Pair(left, right)))
        .map(pair -> pair.left * pair.right)
        .filter(number -> isPalindrome(
            String.valueOf(number)
                .chars()
                .boxed()
                .collect(toList())))
        .max(naturalOrder())
        .orElseThrow(IllegalArgumentException::new);
  }

  private static class Pair {

    private final int left;
    private final int right;

    private Pair(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }
}
