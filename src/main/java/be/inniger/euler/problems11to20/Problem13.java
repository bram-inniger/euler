package be.inniger.euler.problems11to20;

import be.inniger.euler.Problem;

import java.math.BigInteger;
import java.util.List;

import static be.inniger.euler.util.CollectionUtil.readProblemDataAndTransform;
import static java.util.stream.Collectors.toUnmodifiableList;

/*
 * Large sum
 *
 * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
 */
public class Problem13 implements Problem {

  private static final int NR_DIGITS = 10;

  @Override
  public long solve() {
    return readNumbers().stream()
        .reduce(BigInteger::add)
        .map(BigInteger::toString)
        .map(sum -> sum.substring(0, NR_DIGITS))
        .map(Long::valueOf)
        .orElseThrow();
  }

  private List<BigInteger> readNumbers() {
    return readProblemDataAndTransform(this, lines ->
        lines.map(BigInteger::new)
            .collect(toUnmodifiableList()));
  }
}
