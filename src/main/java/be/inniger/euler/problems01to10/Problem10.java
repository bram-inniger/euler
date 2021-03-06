package be.inniger.euler.problems01to10;

import be.inniger.euler.Problem;
import be.inniger.euler.util.Math;

import static be.inniger.euler.util.Math.getPrimesUpUntil;

/*
 * Summation of primes
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
public class Problem10 implements Problem {

  private static final int MAX_VALUE = 2_000_000;

  @Override
  public long solve() {
    return getPrimesUpUntil(MAX_VALUE)
        .stream()
        .map(Math::toLong)
        .reduce(0L, Math::sum);
  }
}
