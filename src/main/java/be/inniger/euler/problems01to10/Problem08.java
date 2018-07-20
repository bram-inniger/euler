package be.inniger.euler.problems01to10;

import be.inniger.euler.util.Math;

import java.util.List;
import java.util.stream.IntStream;

import static be.inniger.euler.util.StreamUtil.readProblemDataAndTransform;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * Largest product in a series
 * <p>
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
public class Problem08 {

  private static final int NR_ADJACENT_DIGITS = 13;
  private final List<Integer> number = readNumber();

  public long solve() {
    return IntStream.rangeClosed(0, number.size() - NR_ADJACENT_DIGITS)
        .boxed()
        .map(beginIndex -> number.subList(beginIndex, beginIndex + NR_ADJACENT_DIGITS))
        .mapToLong(digits -> digits.stream()
            .map(Math::toLong)
            .reduce(1L, Math::multiply))
        .max()
        .orElseThrow();
  }

  private List<Integer> readNumber() {
    return readProblemDataAndTransform("Problem08", lines ->
        lines.flatMap(line -> line.chars()
            .boxed()
            .map(Character::getNumericValue))
            .collect(toUnmodifiableList()));
  }
}
