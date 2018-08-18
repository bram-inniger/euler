package be.inniger.euler.problems11to20;

import java.math.BigInteger;
import java.util.List;

import static be.inniger.euler.util.StreamUtil.readProblemDataAndTransform;
import static java.util.stream.Collectors.toList;

/**
 * Large sum
 * <p>
 * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
 */
public class Problem13 {

  private static final int NR_DIGITS = 10;

  public long solve() {
    return readNumbers().stream()
        .reduce(BigInteger::add)
        .map(BigInteger::toString)
        .map(sum -> sum.substring(0, NR_DIGITS))
        .map(Long::valueOf)
        .orElseThrow();
  }

  private List<BigInteger> readNumbers() {
    return readProblemDataAndTransform("Problem13", lines ->
        lines.map(BigInteger::new)
            .collect(toList()));
  }
}
